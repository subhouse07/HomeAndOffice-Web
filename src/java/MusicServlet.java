/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author andy
 */
public class MusicServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //servlet variables
        String url = "/index.jsp";
        int trackNum = Integer.parseInt(request.getParameter("trackNum"));
        String action = request.getParameter("action");
        boolean playOnLoad = false;
        
        //get Session
        HttpSession session = request.getSession();
        Album album;
        
        //Check page's intended action
        switch (action) {
            
            //Load new album
            case "newAlbum":
                File musicPath = new File(getServletContext().getInitParameter("AlbumHome"));
                String albumId = request.getParameter("albumId");
                String albumPath = musicPath.getAbsolutePath().concat("/" + albumId);
                String[] trackPaths = getPathNames(albumPath, albumId);
                Object[] xmlInfo = readXML(trackPaths[trackPaths.length-1]); //0: artist 1:album 2:tracks
                album = new Album(albumPath, albumId, trackPaths, xmlInfo);
                trackNum = 0;
                session.setAttribute("album", album);
                break;
                
            //go to next track
            case "nextTrack":
                int totalTracks = ((Album) session.getAttribute("album")).
                        getTrackNames().length;
                if (trackNum+1 == totalTracks)
                    trackNum = 0;
                else
                    trackNum++;
                playOnLoad = true;
                break;
                
            //go to previous track
            case "backTrack":
                if (trackNum > 0)
                    trackNum--;
                playOnLoad = true;
                break;
                
            default:
                break;
        }
        
        //set request attributes
        request.setAttribute("playOnLoad", playOnLoad);
        request.setAttribute("trackNum", trackNum);
        
        //forward page
        getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    //get all files in album directory (songs and xml file)
    //these are used for the html audio element's src attribute
    //The xml is for displaying all the album info on the page
    private String[] getPathNames(String albumPath, String albumId) {
        
        File folder = new File(albumPath);
        File[] files = folder.listFiles();
        String[] paths = new String[files.length];
        String xmlPath = "";
        int i = 0;
        
        //Iterate through files in album directory
        //Identify xml file and add at last index of paths array.
        for (File f : files) {
            StringBuilder s = new StringBuilder(f.getName());
            if (f.isFile()) {
                if (!(f.getName().substring(
                        s.lastIndexOf(".")).equals(".xml")))
                {
                    paths[i] = albumId + "/" + f.getName();
                    i++;
                } else
                    xmlPath = albumPath + "/" + f.getName();
            }
        }
        paths[paths.length-1] = xmlPath;
        
        return paths;
    }
    
    
    //Extract album info from xml file
    private Object[] readXML(String xmlPath) {
        
        // This array holds two strings (album title and artist) and
        // a string array (track names)
        Object[] xmlInfo = new Object[3];
        
        String albumTitle;
        String artist;
        String[] trackNames;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlPath));
            Element root = document.getDocumentElement();
            
            NodeList artistNode = root.getElementsByTagName("album_artist");
            NodeList albumNode = root.getElementsByTagName("album_name");
            NodeList tracksNode = root.getElementsByTagName("track");
            
            if (artistNode != null && artistNode.getLength() > 0) {
                artist = artistNode.item(0).getTextContent();
                xmlInfo[0] = artist;
            } else {
                xmlInfo[0] = null;
            }
            if (albumNode != null && albumNode.getLength() > 0) {
                albumTitle = albumNode.item(0).getTextContent();
                xmlInfo[1] = albumTitle;
            } else {
                xmlInfo[1] = null;
            }
            if (tracksNode != null && tracksNode.getLength() > 0) {
                trackNames = new String[tracksNode.getLength()];
                for (int i=0; i<tracksNode.getLength(); i++) {
                    trackNames[i] = tracksNode.item(i).getTextContent();
                }
                xmlInfo[2] = trackNames;
            } else {
                xmlInfo[2] = null;
            }
            
        } catch (ParserConfigurationException e) {
            Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, e);
        } catch (SAXException | IOException e) {
            xmlInfo[0] = null;
            xmlInfo[1] = null;
            xmlInfo[2] = null;
        }
        
        return xmlInfo;
    }
    

}

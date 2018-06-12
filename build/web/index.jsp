<%-- 
    Document   : index
    Created on : Feb 3, 2018, 7:48:16 PM
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home & Office Home Page - Optimized For Personal Home Computer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
	<link rel="stylesheet" href="css/styles.css" type="text/css"/>
	<link rel="stylesheet" type="text/css"
		media="screen and (max-device-width: 1024px)"
		href="css/mobileStyles.css"/>
	<script type="text/javascript" src="js/scripts.js"></script> 
    </head>
    <body onload="loadDefaultAlbum()">
	<div class="header">
            <h1>HOME&OFFICE</h1>
	</div>			
        <div class="container" id="parentWin">		
            
            <div class="computer">
                <img src="img/computer.png" alt="whoops?" id="computer">
            </div>
            
            <div class="row" id="mediaInfo">
                <div>Artist:</div>
                <div>Album:</div>
                <div>Song:</div>
                <div>Seekbar:</div>
            </div>
            
            <div class="row" id="buttons">
                <i class="btns cntrlBack" id="cntrlBack" 
                   onclick="backTrack(${trackNum})"></i>
                <i class="btns cntrlPlay" id="cntrlPlay" 
                   onclick="startPlayer()"></i>
                <i class="btns cntrlPause" id="cntrlPause" 
                   onclick="pausePlayer()"></i>
                <i class="btns cntrlSkip" id="cntrlSkip" 
                   onclick="nextTrack(${trackNum})"></i>
            </div>
            
            <div class="audioPlayer">
                <audio id="testAudio" onended="nextTrack(${trackNum})">
                    <source id="currentTrack" src="${album.trackPaths[trackNum]}" type="audio/ogg">
                    Something wrong.				
                </audio>
            </div>
            
            <div>
                <input type="hidden" id="playOnLoad" value="${playOnLoad}">
                <form id="musicSelection" method="post" action="Player">
                    <div class="musicChoice">
                        <input type="hidden" name="action" id="action" value="">
                        <input type="hidden" name="albumId" id="albumId" value="hOffice">
                        <input type="hidden" name="trackNum" id="trackNum" value="${trackNum}">

                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

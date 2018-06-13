# HomeAndOffice-Web

## Intro

This document is just here to serve as a basic overview of the HomeAndOffice website, what it does, how it works currently, and what I would like to do with it in the future.

The work that I started doing on this project eventually got neglected due to my work on the HomeAndOffice Android app, along with general busy-ness of school and work. I would like to figure out what exactly I want out of this site and finish it, though.

The purpose of this website is pretty straightforward. It serves as a media player for any HomeAndOffice material. At the moment, I don't plan on doing much more than including controls for the player, some animated graphics/flare, and an album selector.

## How It Works

This page is controlled largely by a Java servlet which constructs an Album object by parsing an XML file. The Album object is made up of some strings:
* Album title
* Artist
* Album path
* Album ID
* Track Titles
* Track Paths

The servlet returns a JSP which can interact with this album via a Javascript media player. That is really all this website does at the moment!

## Where It's Going

The page is still in its infancy and I would be comfortable scrapping the entire thing to start over from scratch and maybe use something other than JSP to make it work. 

The corresponding Android app makes use of a JSON file which holds all the information needed to play all the HomeAndOffice albums so the first step in proceeding on this project is change from parsing an XML to JSON.

Aside from that I may try to do an Angular implementation of the website. So all in all, I will most likely just be using the project in its current state as a launching point for whatever it is to become.

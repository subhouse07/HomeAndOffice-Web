var trackNum = 0;

function loadDefaultAlbum() {
    var playOnLoad = document.getElementById("playOnLoad").value;
    if (document.getElementById("currentTrack").getAttribute("src") == "") {
        document.getElementById("action").setAttribute("value", "newAlbum");
        document.getElementById("trackNum").setAttribute("value", "0");
        document.getElementById("musicSelection").submit();
    } else if (playOnLoad == "true") {
        startPlayer();
    }
    
}

function startPlayer() {
    var aud = document.getElementById("testAudio");
    aud.play();
}

function pausePlayer() {
	var aud = document.getElementById("testAudio");
	aud.pause();
}

function backTrack(trackNum) {
    if (document.getElementById("currentTrack").getAttribute("src") != "") {
        document.getElementById("action").setAttribute("value", "backTrack");
        document.getElementById("trackNum").setAttribute("value", trackNum);
        document.getElementById("musicSelection").submit();
    }
}

function nextTrack(trackNum) {
    if (document.getElementById("currentTrack").getAttribute("src") != "") {
        document.getElementById("action").setAttribute("value", "nextTrack");
        document.getElementById("trackNum").setAttribute("value", trackNum);
        document.getElementById("musicSelection").submit();
    }
}
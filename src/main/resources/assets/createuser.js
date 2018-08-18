function createUser() {
    var hash    = location.hash.substr(1);
    var twitchToken = hash.substr(hash.indexOf('access_token='))
                          .split('&')[0]
                          .split('=')[1];
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "PUT", "api/createuser/" + twitchToken, false ); // false for synchronous request
    xmlHttp.send( null );
    var response = JSON.parse(xmlHttp.responseText);
    var messageNode = document.getElementById('message');
    messageNode.innerHTML = "";
    var text;
    if ("name" in response) {        
        text = document.createTextNode("Successfully set up user \"" + response.name + "\". Now paste this authkey into the tracker options: ");
        document.getElementById('token').value = response.token;
    } else {
        text = document.createTextNode("The isaac tracker server gave an error; please report this bug. Error code: " + response.code + ". Message: \"" + response.message + "\"");        
    }
    messageNode.appendChild(text);
    
    console.log();
}

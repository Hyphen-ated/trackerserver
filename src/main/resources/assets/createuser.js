function createUser() {
    var hash    = location.hash.substr(1);
    var twitchToken = hash.substr(hash.indexOf('access_token='))
                          .split('&')[0]
                          .split('=')[1];
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "PUT", "api/createuser/" + twitchToken, false ); // false for synchronous request
    xmlHttp.send( null );
    var response = JSON.parse(xmlHttp.responseText);
    document.getElementById('message').innerHTML = "Successfully set up user \"" + response.name + "\". Now paste this authkey into the tracker options: ";
    document.getElementById('token').value = response.token;
    console.log();
}


function muestraInfo( divId ) {
	var vector = document.getElementsByClassName( "estaVisible" );
	var i;
	for( i = 0; i < vector.length; i++ ) {
		vector[i].className = "hidden";
	}
	document.getElementById( divId ).className = "visible estaVisible";
}
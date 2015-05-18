
function checkPasswords() {
	   		var p1 = document.getElementById( "password" ).value;
	   		var p2 = document.getElementById( "passwordRepeat" ).value;
	   		if( p1 == p2 ) {
	   			return true;
	   		}
	   		var divPwd = document.getElementById( "checkPasswords" );
	   		divPwd.innerHTML = "Las contraseñas no son iguales";
	   		divPwd.className = "error";
	   		return false;
}
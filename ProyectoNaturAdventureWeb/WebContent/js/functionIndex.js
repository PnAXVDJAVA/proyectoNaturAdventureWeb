function mov() {
		setInterval( function() {
			var centro = document.getElementsByTagName('img')[2];
			var der = document.getElementsByTagName('img')[3];
			document.getElementById("imgs").className = "movimiento";
			der.className = "aparecer";
			centro.className = "desaparecer";
			
			setTimeout( function() {
				var izqOculta = document.getElementsByTagName('img')[0];
				var izq = document.getElementsByTagName('img')[1];
				var derOculta = document.getElementsByTagName('img')[4];

				der.className = "der";
				centro.className = "centro";

				izqOculta.src = izq.src;
				izqOculta.id = izq.id;
				//derOculta.className = der.className;

				izq.src = centro.src;
				izq.id = centro.id;
				//der.className = centro.className;

				centro.src = der.src;
				centro.id = der.id;
				//centro.className = izq.className;

				der.src = derOculta.src;
				der.id = derOculta.id;
				//izq.className = izqOculta.className;

				if(der.id == "16") {
					derOculta.src = "images/1.jpg";
					derOculta.id = "1";
				}
				else {
					derOculta.src = "images/" + ( parseInt(derOculta.id) + 1 ) + ".jpg";
					derOculta.id = ( parseInt(derOculta.id) + 1 );
					
				}
				//izqOculta.className = "izqOculta";
				document.getElementById("imgs").className = "normal";
			}, 2500);
		}, 6000);
}
function mov() {
		setInterval( function() {
			var centro = document.getElementsByClassName('slider')[2];
			var der = document.getElementsByClassName('slider')[3];
			document.getElementById("imgs").className = "movimiento";
			der.className = "slider aparecer";
			centro.className = "slider desaparecer";
			
			setTimeout( function() {
				var izqOculta = document.getElementsByClassName('slider')[0];
				var izq = document.getElementsByClassName('slider')[1];
				var derOculta = document.getElementsByClassName('slider')[4];

				der.className = "slider der";
				centro.className = "slider centro";

				izqOculta.src = izq.src;
				izqOculta.id = izq.id;

				izq.src = centro.src;
				izq.id = centro.id;

				centro.src = der.src;
				centro.id = der.id;

				der.src = derOculta.src;
				der.id = derOculta.id;

				if(der.id == "16") {
					derOculta.src = "images/slider/1.jpg";
					derOculta.id = "1";
				}
				else {
					derOculta.src = "images/slider/" + ( parseInt(derOculta.id) + 1 ) + ".jpg";
					derOculta.id = ( parseInt(derOculta.id) + 1 );
					
				}
				document.getElementById("imgs").className = "normal";
			}, 2500);
		}, 6000);
}
function storyAppear() {
	var first = document.getElementById("first").value;
	var second = document.getElementById("second").value;
	var third = document.getElementById("third").value;
	var fourth = document.getElementById("fourth").value;
	var fifth = document.getElementById("fifth").value;
	var sixth = document.getElementById("sixth").value;
	var seventh = document.getElementById("seventh").value;
	var twelve = document.getElementById("twelve").value;
	var thirteen = document.getElementById("thirteen").value;
	var fourteen = document.getElementById("fourteen").value;
	var fifteen = document.getElementById("fifteen").value;
	var sixteen = document.getElementById("sixteen").value;
	var seventeen = document.getElementById("seventeen").value;
	document.getElementById("message").innerHTML =
		"One " + first + "  day, you step through a magical gate and are greeted by a/an "
		+ second + ". It leads you to a/an " + third + " cottage full of " + fourth +
		". As you enter, the door closes with a/an " + fifth + ". The " + sixth + " " +
		second + " turns into a gremlin and grins " + seventh + ", while the crowd of " +
		fourth + " begins to approach. You turn around to flee, but the gremlin is already "
		+ "blocking the door! A/An " + twelve + " is lying beside you, so you grab it and " + thirteen
		+ " smack the gremlin and his companions. You " + fourteen + " to the door and open" 
		+ " it with a tug. A/An " + fifteen + " " + sixteen + " appears and thanks you for" +
		" defeating the evildoers. It bestows upon you the honor of becoming the ruler" +
		" of " + seventeen + "land forevermore!";
	
}
$(document).ready(function() {
	$.mask.definitions['d'] = "[A-ZÄ„Ä†Ĺ�Ĺ�Ă“ĹšĹ»Ĺą]"; // DuĹĽe litery
	$.mask.definitions['p'] = "[A-Za-zÄ…Ä„Ä‡Ä†Ä™Ä�Ĺ‚Ĺ�Ĺ„Ĺ�ĂłĂ“Ĺ›ĹšĹĽĹ»ĹşĹą]"; // litery + polskie znaki
	$.mask.definitions['n'] = "[A-Za-z-Ä…Ä„Ä‡Ä†Ä™Ä�Ĺ‚Ĺ�Ĺ„Ĺ�ĂłĂ“Ĺ›ĹšĹĽĹ»ĹşĹą]"; // litery + polskie znaki + myĹ›lnik
 	$.mask.definitions['x'] = "[A-Za-z0-9-Ä…Ä„Ä‡Ä†Ä™Ä�Ĺ‚Ĺ�Ĺ„Ĺ�ĂłĂ“Ĺ›ĹšĹĽĹ»ĹşĹą,]"; // litery + liczby + polskie znaki + przecinek + myĹ›lnik
 	$.mask.definitions['c'] = "[A-Za-z0-9-Ä…Ä„Ä‡Ä†Ä™Ä�Ĺ‚Ĺ�Ĺ„Ĺ�ĂłĂ“Ĺ›ĹšĹĽĹ»ĹşĹą./]"; // litery + liczby + polskie znaki +  myĹ›lnik + kropka + slash
 	$.mask.definitions['e'] = "[A-Za-z0-9-_@.]"; // email
 	$.mask.definitions['t'] = "[A-Z0-9]"; // Wielkie litery + Liczby
 	$.mask.definitions['q'] = "[0-9]"; // liczby + kropka
		
	$("#name").mask("t?ttttttttttttttttttttttttttttttttttttttttttttttttt",{placeholder:""});
	$("#companyName").mask("dc?cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc",{placeholder:""});
	$("#pesel").mask("99999999999");
	$("#nip").mask("999-999-99-99");
	$("#regon").mask("999999999");
	$("#regon").mask("99999999999999");
	$("#phone").mask("+48 999-999-999");
	$("#email").mask("eeeee?eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",{placeholder:""});
	$("#confirmEmail").mask("eeeee?eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",{placeholder:""});
}); 
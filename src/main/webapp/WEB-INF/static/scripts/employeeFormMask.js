$(document).ready(function() {
	$.mask.definitions['d'] = "[A-ZĄĆŁŃÓŚŻŹ]"; // Duże litery
	$.mask.definitions['p'] = "[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ]"; // litery + polskie znaki
	$.mask.definitions['n'] = "[A-Za-z-ąĄćĆęĘłŁńŃóÓśŚżŻźŹ]"; // litery + polskie znaki + myślnik
 	$.mask.definitions['x'] = "[A-Za-z0-9-ąĄćĆęĘłŁńŃóÓśŚżŻźŹ,]"; // litery + liczby + polskie znaki + przecinek + myślnik
 	$.mask.definitions['e'] = "[A-Za-z0-9-_@.]"; // email
		
	$("#name").mask("dp?pppppppppppppppppppppppppppppppppppppppppppppppp",{placeholder:""});
	$("#surname").mask("dn?nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn",{placeholder:""});
	$("#pesel").mask("99999999999");
	$("#nip").mask("999-999-99-99");
	$("#phone").mask("+48 999-999-999");
	$("#address").mask("x?xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",{placeholder:""});
	$("#email").mask("eeeee?eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",{placeholder:""});
	$("#confirmEmail").mask("eeeee?eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",{placeholder:""});
}); 
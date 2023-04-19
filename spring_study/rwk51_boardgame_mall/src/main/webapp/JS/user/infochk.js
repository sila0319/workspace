/**
 * 
 */
const infoDir = 'rwk51_boardgame_mall/JS/user/user.jsp';
let ID_chk = false;
let PW_chk = false;
let rrn_chk = false;
let EMAIL_chk = true;
let PHONE_chk = true;
 

function chkform(){
	
	console.log("아이디 : "+ID_chk);
	console.log("비번 : "+PW_chk);
	console.log("주번 : "+rrn_chk);
	console.log("주번 : "+EMAIL_chk);
	console.log("주번 : "+PHONE_chk);
	if(ID_chk && PW_chk && rrn_chk && EMAIL_chk && PHONE_chk){
		return true;
	}else{
		return false;
	}
		
}
function chkajax(actionType, datas){
	var success_data = false;
	$.ajax({
		url:'/rwk51_boardgame_mall/JS/user/user.jsp' ,
		type:'post',
		async: false,
		dataType: "json",
		data:{actionType: actionType, user_data: datas},
		success:function(data){
			success_data = data.checkResult;
		
			console.log(data);
			console.log(success_data);
					
		},
	});	
	return success_data;
}

function id_chk(){
	const id = document.querySelector('#user_id').value;
	
	var success_data = chkajax("user_id",id);
	
	
	
	if(id===""){
		document.querySelector('#id_chk').innerHTML = "아이디를 입력해주세요.";
		ID_chk = false;
	}else{
		if(success_data === true){//아이디 사용가능
			document.querySelector('#id_chk').innerHTML = "사용가능한 아이디입니다.";
			ID_chk = true;
		}else if(success_data === false){
			document.querySelector('#id_chk').innerHTML = "사용 불가능한 아이디입니다.";
			ID_chk = false;
		}
	}
	
}

function pw_chk(){
	if(document.querySelector('#user_pw').value === document.querySelector('#user_repw').value){
		document.querySelector('#pw_chk').innerHTML = "비밀번호 일치합니다.";
		PW_chk = true;
	}else{
		document.querySelector('#pw_chk').innerHTML = "비밀번호 불일치합니다.";
		PW_chk = false;
	}
}

const RRN_chk = (target)=>{
	var success_data = false;
	console.log(target.value);
	rrn_chk = false;
	if(target.value.length!=13){
		document.querySelector('#RRN_chk').innerHTML = "주민등록번호를 다시입력해주세요.";
	}else{
		 var ssn1    = target.value.slice(0,6),
        ssn2    = target.value.slice(6),
        ssn     = target.value,
        arr_ssn = [],
        compare = [2,3,4,5,6,7,8,9,2,3,4,5],
        sum     = 0;
        console.log(ssn1+" ",ssn2);
           // 공식: M = (11 - ((2×A + 3×B + 4×C + 5×D + 6×E + 7×F + 8×G + 9×H + 2×I + 3×J + 4×K + 5×L) % 11)) % 10
	    for (var i = 0; i<13; i++) { 
	        arr_ssn[i] = ssn.substring(i,i+1); 
	    }
	    
	    for (var i = 0; i<12; i++){
	        sum = sum + (arr_ssn[i] * compare[i]); 
	    }
	 
	    sum = (11 - (sum % 11)) % 10;
	    
	    if (sum != arr_ssn[12])
	    { 
			document.querySelector('#RRN_chk').innerHTML = "올바른 주민등록번호를 입력하여 주세요";
			
	     
	       
	    }else{
			success_data =  chkajax("user_RRN",target.value);
			if(success_data){
				document.querySelector('#RRN_chk').innerHTML = "올바른 주민등록번호입니다.";
				rrn_chk = true;
			}else{
					document.querySelector('#RRN_chk').innerHTML = "올바른 주민등록번호를 입력하여 주세요";
					
			}
			
		}
        
        
		
	}
}

function email_chk(){
	const email = document.querySelector('#user_email').value;
	
	var success_data = chkajax("user_email",email);
	
	
	
	if(email===""){
		document.querySelector('#email_chk').innerHTML = "";
		EMAIL_chk = true;
	}else{
		if(success_data === true){//아이디 사용가능
			document.querySelector('#email_chk').innerHTML = "사용가능한 이메일입니다.";
			EMAIL_chk = true;
			
		}else if(success_data === false){
			document.querySelector('#email_chk').innerHTML = "사용 불가능한 이메일입니다.";
			EMAIL_chk = false;
		}
	}
	
}

const phone_chk = (target) => {
	console.log(target.value);
		var phoneRule = /^(010){1}[0-9]{4}[0-9]{4}$/;
	console.log(target.value.length);
	if(target.value===""){
		PHONE_chk = true;
		document.querySelector('#phone_chk').innerHTML = "";
	}
	else if(phoneRule.test(target.value)){
		success_data =  chkajax("user_phone",target.value);
		if(success_data){
			document.querySelector('#phone_chk').innerHTML = "사용가능한 휴대폰 번호입니다.";
			PHONE_chk = true;
		}else{
			document.querySelector('#phone_chk').innerHTML = "사용 불가능한 휴대폰 번호입니다.";
			PHONE_chk = false;
		}
	}else{
		document.querySelector('#phone_chk').innerHTML = "휴대폰 번호를 확인해주세요.";
		PHONE_chk = false;
	}
	console.log(PHONE_chk +"휴대폰 사용가능여부 체크");
	console.log(phoneRule.test(target.value));
	
            
}












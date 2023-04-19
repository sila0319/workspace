/**
 * 
 */
 function b_deletechk(){
	if(window.confirm("삭제하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}



function b_buychk(){
	
	if(window.confirm("상품 구매 하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}

function cnt_chk(){
	const c_cnt = document.querySelector('#c_cnt').value;
	const b_price = document.querySelector('#b_price').value;
	if(c_cnt<0){
		document.querySelector('#c_cnt').value = 1;
		return alert("0개 구매불가능합니다.")
	}
	console.log("사용중")
	
	document.querySelector('#cart_price').value = c_cnt*b_price;
	return c_cnt * b_price;
	
}

function l_chk(){
	alert("로그인 바랍니다.")
	return false;	
}




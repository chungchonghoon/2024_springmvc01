<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

		 fieldset {
        width: 800px;           /* 원하는 너비 설정 */
        margin: 20px auto;    /* 수평 중앙 정렬 */
        border: 1px solid black;
        padding: 10px;
    	}
		
		table { width: auto;
					margin: 0 auto;
					text-align: center;
		}
		
		td { 
				height: 20px;
				text-align: left;
		}
		
		table, td{
							border: 1px solid black;
		}
		input {padding: 5px; margin: 5px;}
</style>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="top.jsp" />
			<fieldset style="width: 50%;">
				<legend>상품 등록 페이지 </legend>
				
				<form action="/shop_product_insert_ok" method="post" enctype="multipart/form-data">
					<table>
						<tbody>
							<tr>
								<td>분류</td>
								<td>
					                <input type="radio" name="category" value="com001" id="computer" required>
					                <label for="computer">컴퓨터</label>
					                <input type="radio" name="category" value="ele002" id="appliances">
					                <label for="appliances">가전 제품</label>
					                <input type="radio" name="category" value="sp003" id="sports">
					                <label for="sports">스포츠</label>
					            </td>
							</tr>
							<tr>
								<td>제품번호</td>
								<td><input type="text" name="p_num" required></td>
							</tr>
							<tr>
								<td>제품명</td>
								<td><input type="text" name="p_name" required></td>
							</tr>
							<tr>
								<td>판매사</td>
								<td colspan="2"><input type="text" name="p_company" required></td>
							</tr>
							<tr>
								<td>상품가격</td>
								<td><input type="number" name="p_price" required></td>
							</tr>
							<tr>
								<td>할인가</td>
								<td><input type="number" name="p_saleprice" required></td>
							</tr>
							<tr>
								<td>상품이미지s</td>
								<td><input type="file" name="file_s" required></td>
							</tr>
							<tr>
								<td>상품이미지L</td>
								<td><input type="file" name="file_l" required></td>
							</tr>
							<tr>
								<td>상품내용</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><textarea rows="6" cols="40" name="p_content"></textarea></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="상품등록"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</fieldset>
</body>
</html>
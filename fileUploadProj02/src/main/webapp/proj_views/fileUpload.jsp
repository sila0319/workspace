<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="java.sql.*" %>
<%@page import="java.io.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>파일 업로드/다운로드 테스트 페이지 with COS</h1>
<h2>파일 업로드 진행 - COS, DB 활용</h2>
<hr>
<%
request.setCharacterEncoding("utf-8");
String imgDirPath = "D:\\dev\\workspace\\fileUploadProj02\\src\\main\\webapp\\image";

int maxSize = 1024*1024*5;

MultipartRequest multi = new MultipartRequest(request,imgDirPath+"//original",maxSize,"utf-8",new DefaultFileRenamePolicy());
String userName = multi.getParameter("userName");

Enumeration<?> files = multi.getFileNames();

String element ="";
String filesystemName ="";
String originalFileName ="";
String contentType ="";
long length =0;

if(files.hasMoreElements()){
   element = (String)files.nextElement(); 
   filesystemName = multi.getFilesystemName(element);
   originalFileName = multi.getOriginalFileName(element);
   contentType = multi.getContentType(element);
   length = multi.getFile(element).length();
}


%>

<p>입력한 사용자 이름 : <%=userName %></p>
<p>파라메타 이름 : <%=element %></p>
<p>서버에 업로드된 파일 이름 : <%=filesystemName %></p>
<p>유저가 업로드한 원본 파일 이름 : <%=originalFileName %></p>
<p>파일 타입 : <%=contentType %></p>
<p>파일 크기 : <%=length %></p>
<%
String driverName = "org.mariadb.jdbc.Driver";
String url = "jdbc:mariadb://localhost/rwk51_mall_db";
String user = "root";
String password = "maria";

int result;

Class.forName(driverName);
Connection con = DriverManager.getConnection(url,user,password);

PreparedStatement pstmt = null;

String sql = "insert into image_tbl(i_file_name, i_original_name, i_thumbnail_name, i_file_type, i_file_size) values(?,?,?,?,?)";

try {
   pstmt = con.prepareStatement(sql);
   pstmt.setString(1,filesystemName);
   pstmt.setString(2,originalFileName);
   pstmt.setString(3,"sm_" + filesystemName);
   pstmt.setString(4,contentType);
   pstmt.setLong(5,length);

   result = pstmt.executeUpdate();
   
   if(result == 1){
      out.println("<h2>데이터베이스 입력 성공</h2>");
   }
   else{
      out.println("<h2>데이터베이스 입력 실패<h2>");
   }
}catch(SQLException e){
   e.printStackTrace();
}finally{
   con.close();
}

//String oPath = "C:/Temp/40f0594a-b3f6-4c0f-a0b2-3cebbaf0d74e.jpg"; // 원본 경로
File oFile = new File(imgDirPath);

int index = imgDirPath.lastIndexOf(".");
String ext = imgDirPath.substring(index + 1); // 파일 확장자

String tPath = oFile.getParent() + File.separator + "t-" + oFile.getName(); // 썸네일저장 경로
File tFile = new File(tPath);

double ratio = 2; // 이미지 축소 비율

try {
   BufferedImage oImage = ImageIO.read(oFile); // 원본이미지
   int tWidth = (int) (oImage.getWidth() / ratio); // 생성할 썸네일이미지의 너비
   int tHeight = (int) (oImage.getHeight() / ratio); // 생성할 썸네일이미지의 높이
   
   BufferedImage tImage = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // 썸네일이미지
   Graphics2D graphic = tImage.createGraphics();
   Image image = oImage.getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
   graphic.drawImage(image, 0, 0, tWidth, tHeight, null);
   graphic.dispose(); // 리소스를 모두 해제

   ImageIO.write(tImage, ext, tFile);
} catch (IOException e) {
   e.printStackTrace();
}
%>

<p><br><a href="../index.jsp">홈으로 돌아가기</a>
</body>
</html>
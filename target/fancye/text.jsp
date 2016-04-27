<!DOCTYPE html>
<html>
<head>
<title>微信公众平台接口调试工具</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://v3.bootcss.com/examples/signin/signin.css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>  
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>  
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>  
    <![endif]-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-signin" role="form" action="wxtest.do">
			<h2 class="form-signin-heading">消息接口调试</h2>
			<input type="text" name="URL" class="form-control" placeholder="开发者填写URL，调试时将把消息推送到该URL上" required autofocus>
			<input type="text" name="ToUserName" class="form-control" placeholder="开发者微信号" required autofocus>
			<input type="text" name="FromUserName" class="form-control" placeholder="发送方帐号（一个OpenID）" required autofocus>
			<input type="text" name="CreateTime" class="form-control" placeholder="消息创建时间 （整型）" required autofocus>
			<input type="text" name="MsgType" class="form-control" placeholder="消息类型（文本消息为 text ）" required autofocus>
			<input type="text" name="Content" class="form-control" placeholder="消息类型（文本消息内容）" required autofocus>
			<input type="text" name="MsgId" class="form-control" placeholder="消息类型（消息id，64位整型）" required autofocus>
			<br/>
			<button class="btn btn-lg btn-primary btn-block" type="submit">测试</button>
		</form>
	</div>
</body>
</html>
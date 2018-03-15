function function1() {
	var agent = navigator.userAgent.toLowerCase() ;
 		var version = browserVersion(agent) ;
		var name = browserName(agent);
		var result = theJavaFunction(version,name);
		if (result != null) {
		document.write(result);
		}
}

function browserVersion(ua){

 var Sys = {};
    
    var s;
    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
 
   //返回版本号
    if (Sys.ie) {
    	//document.write('IE: ' + Sys.ie);
    	return Sys.ie;
    }
    if (Sys.firefox) {
    	//document.write('Firefox: ' + Sys.firefox);
    	return Sys.firefox;
    }
    if (Sys.chrome) {
    	//document.write('Chrome: ' + Sys.chrome);
    	return Sys.chrome;
    }
    if (Sys.opera) {
    	//document.write('Opera: ' + Sys.opera);
    	return Sys.opera;
    }
    if (Sys.safari){
    	// document.write('Safari: ' + Sys.safari);
    	 return Sys.safari;
    }
     
    
}

function browserName(agent)
{


//IE
if(agent.indexOf("msie") > 0)
{
return "IE" ;
}

//firefox
if(agent.indexOf("firefox") > 0)
{
return "firefox" ;
}

//Chrome
if(agent.indexOf("chrome") > 0)
{
return "Chrome" ;
}

//Safari
if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0)
{
return "Safari" ;
}

}
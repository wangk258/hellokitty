function uParse(e,t){var n=!!window.ActiveXObject,i=n?function(e,t,n){var i,a;n=n||document,i=n.indexList?n.indexList:n.indexList={};var l;if(i[e])l=n.styleSheets[i[e]];else{if(void 0===t)return"";l=n.createStyleSheet("",a=n.styleSheets.length),i[e]=a}return void 0===t?l.cssText:void(l.cssText=l.cssText+"\n"+(t||""))}:function(e,t,n){n=n||document;var i,a=n.getElementsByTagName("head")[0];if(!(i=n.getElementById(e))){if(void 0===t)return"";i=n.createElement("style"),i.id=e,a.appendChild(i)}return void 0===t?i.innerHTML:void(""!==t?i.innerHTML=i.innerHTML+"\n"+t:a.removeChild(i))},a=function(e){var t=window.document;"complete"===t.readyState?e():n?(!function(){if(!t.isReady){try{t.documentElement.doScroll("left")}catch(e){return void setTimeout(arguments.callee,0)}e()}}(),window.attachEvent("onload",function(){e()})):(t.addEventListener("DOMContentLoaded",function(){t.removeEventListener("DOMContentLoaded",arguments.callee,!1),e()},!1),window.addEventListener("load",function(){e()},!1))},l=function(e,t,n){if(null!=e)if(e.length===+e.length){for(var i=0,a=e.length;i<a;i++)if(t.call(n,e[i],i,e)===!1)return!1}else for(var l in e)if(e.hasOwnProperty(l)&&t.call(n,e[l],l,e)===!1)return!1},d=function(e,t){var n=-1;return l(e,function(e,i){if(e===t)return n=i,!1}),n},r=function(e,t){d(e,t)==-1&&e.push(t)},o=function(){function e(e,n){try{for(var i,a=0;i=t[a++];)if(i.doc===e&&i.url==(n.src||n.href))return i}catch(e){return null}}var t=[];return function(n,i,a){var l=e(n,i);if(l)return void(l.ready?a&&a():l.funs.push(a));if(t.push({doc:n,url:i.src||i.href,funs:[a]}),!n.body){var d=[];for(var r in i)"tag"!=r&&d.push(r+'="'+i[r]+'"');return void n.write("<"+i.tag+" "+d.join(" ")+" ></"+i.tag+">")}if(!i.id||!n.getElementById(i.id)){var o=n.createElement(i.tag);delete i.tag;for(var r in i)o.setAttribute(r,i[r]);o.onload=o.onreadystatechange=function(){if(!this.readyState||/loaded|complete/.test(this.readyState)){if(l=e(n,i),l.funs.length>0){l.ready=1;for(var t;t=l.funs.pop();)t()}o.onload=o.onreadystatechange=null}},o.onerror=function(){throw Error("The load "+(i.href||i.src)+" fails,check the url")},n.getElementsByTagName("head")[0].appendChild(o)}}}(),s={liiconpath:"http://bs.baidu.com/listicon/",listDefaultPaddingLeft:"20",highlightJsUrl:"",highlightCssUrl:"",customRule:function(){}};if(t)for(var c in t)s[c]=t[c];a(function(){var t;if(document.querySelectorAll)t=document.querySelectorAll(e);else if(/^#/.test(e))t=[document.getElementById(e.replace(/^#/,""))];else if(/^\./.test(e)){var t=[];l(document.getElementsByTagName("*"),function(n){n.className&&new RegExp("\\b"+e.replace(/^\./,"")+"\\b","i").test(n.className)&&t.push(n)})}else t=document.getElementsByTagName(e);l(t,function(t){function n(e){l(e,function(e){e.firstChild||(e.innerHTML="&nbsp;")})}function a(t){var n=[],a={cn:"cn-1-",cn1:"cn-2-",cn2:"cn-3-",num:"num-1-",num1:"num-2-",num2:"num-3-",dash:"dash",dot:"dot"};l(t,function(t){if(t.className&&/custom_/i.test(t.className)){var i=t.className.match(/custom_(\w+)/)[1];if("dash"==i||"dot"==i)r(n,e+" li.list-"+a[i]+"{background-image:url("+s.liiconpath+a[i]+".gif)}"),r(n,e+" ul.custom_"+i+"{list-style:none;} "+e+" ul.custom_"+i+" li{background-position:0 3px;background-repeat:no-repeat}");else{var d=1;l(t.childNodes,function(t){"LI"==t.tagName&&(r(n,e+" li.list-"+a[i]+d+"{background-image:url("+s.liiconpath+"list-"+a[i]+d+".gif)}"),d++)}),r(n,e+" ol.custom_"+i+"{list-style:none;}"+e+" ol.custom_"+i+" li{background-position:0 3px;background-repeat:no-repeat}")}switch(i){case"cn":r(n,e+" li.list-"+i+"-paddingleft-1{padding-left:25px}"),r(n,e+" li.list-"+i+"-paddingleft-2{padding-left:40px}"),r(n,e+" li.list-"+i+"-paddingleft-3{padding-left:55px}");break;case"cn1":r(n,e+" li.list-"+i+"-paddingleft-1{padding-left:30px}"),r(n,e+" li.list-"+i+"-paddingleft-2{padding-left:40px}"),r(n,e+" li.list-"+i+"-paddingleft-3{padding-left:55px}");break;case"cn2":r(n,e+" li.list-"+i+"-paddingleft-1{padding-left:40px}"),r(n,e+" li.list-"+i+"-paddingleft-2{padding-left:55px}"),r(n,e+" li.list-"+i+"-paddingleft-3{padding-left:68px}");break;case"num":case"num1":r(n,e+" li.list-"+i+"-paddingleft-1{padding-left:25px}");break;case"num2":r(n,e+" li.list-"+i+"-paddingleft-1{padding-left:35px}"),r(n,e+" li.list-"+i+"-paddingleft-2{padding-left:40px}");break;case"dash":r(n,e+" li.list-"+i+"-paddingleft{padding-left:35px}");break;case"dot":r(n,e+" li.list-"+i+"-paddingleft{padding-left:20px}")}}}),n.push(e+" .list-paddingleft-1{padding-left:0}"),n.push(e+" .list-paddingleft-2{padding-left:"+s.listDefaultPaddingLeft+"px}"),n.push(e+" .list-paddingleft-3{padding-left:"+2*s.listDefaultPaddingLeft+"px}"),i("list",e+" ol,"+e+" ul{margin:0;padding:0;}li{clear:both;}"+n.join("\n"),document)}if("textarea"==t.tagName.toLowerCase()){var d=document.createElement("div");/^#/.test(e)?d.id=e.replace(/^#/,""):/^\./.test(e)&&(d.className=e.replace(/^\./,"")),t.parentNode.insertBefore(d,t),d.innerHTML=t.value,t.parentNode.removeChild(t),t=d}var c={table:function(){i("table",e+" table.noBorderTable td,"+e+" table.noBorderTable th,"+e+" table.noBorderTable caption{border:1px dashed #ddd !important}"+e+" table{margin-bottom:10px;border-collapse:collapse;display:table;}"+e+" td,"+e+" th{ background:white; padding: 5px 10px;border: 1px solid #DDD;}"+e+" caption{border:1px dashed #DDD;border-bottom:0;padding:3px;text-align:center;}"+e+" th{border-top:2px solid #BBB;background:#F7F7F7;}"+e+" td p{margin:0;padding:0;}",document)},ol:a,ul:a,pre:function(e){"undefined"==typeof XRegExp&&o(document,{id:"syntaxhighlighter_js",src:s.highlightJsUrl,tag:"script",type:"text/javascript",defer:"defer"},function(){l(e,function(e){if(/brush/i.test(e.className)){SyntaxHighlighter.highlight(e);for(var t,n=document.getElementsByTagName("table"),i=0;t=n[i++];)if(/SyntaxHighlighter/i.test(t.className))for(var a,l,d=t.getElementsByTagName("td"),r=0;a=d[0].childNodes[r];r++)l=d[1].firstChild.childNodes[r],l&&(l.style.height=a.style.height=l.offsetHeight+"px")}})}),document.getElementById("syntaxhighlighter_css")||o(document,{id:"syntaxhighlighter_css",tag:"link",rel:"stylesheet",type:"text/css",href:s.highlightCssUrl})},td:n,th:n,caption:n};for(var f in c){var u=t.getElementsByTagName(f);u.length&&c[f](u)}s.customRule(t)})})}
function HelmetWebSocket(clientId, messageConsumer, queryString) {
    var obj = new Object();

    obj.initWebsocketUrl = function () {
        if (obj.wsBaseUrl == undefined) {
            var host = window.location.host;
            var isCom80 = host.indexOf(".com") != -1 && (window.location.port == "80" || window.location.port == "");
            obj.wsBaseUrl = "ws://" + host + /*( isCom80 ? ":80" : ":13332") +*/ "/ws/helmet";
        }
    }

    if ('WebSocket' in window) {
        obj.initWebsocketUrl();

        obj.wsFullUrl = obj.wsBaseUrl + (queryString || "");
        console.debug("websocket url=" + obj.wsFullUrl);
        obj.clientId = clientId;
        obj.messageConsumer = messageConsumer;
        obj.websocket = new WebSocket(obj.wsFullUrl);
        console.info("websocket创建完毕." + obj.clientId + ",wsFullUrl=" + obj.wsFullUrl);

//连接成功建立的回调方法
        obj.websocket.onopen = function (event) {
            console.debug("WebSocket连接成功.clientId=" + obj.clientId + ",event=" + JSON.stringify(event) + ",wsFullUrl=" + obj.wsFullUrl);
        }

//接收到消息的回调方法
        obj.websocket.onmessage = function (event) {
            // console.debug('收到websocket消息：'+event.data);
            if (obj.messageConsumer) {
                doCallback(obj.messageConsumer, [event.data]);
            }
        }

//连接发生错误的回调方法
        obj.websocket.onerror = function (event) {
            showAlert('网络连接发生错误，请刷新页面.clientId=' + obj.clientId + ",event=" + JSON.stringify(event) + ",wsFullUrl=" + obj.wsFullUrl);
        };

//连接关闭的回调方法
        obj.websocket.onclose = function (event) {
            console.debug("WebSocket连接关闭.clientId=" + obj.clientId + ",event=" + JSON.stringify(event) + ",wsFullUrl=" + obj.wsFullUrl);
            //showAlert('网络连接已断开，请刷新页面');
        }


        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            console.debug("页面退出，则执行关闭websocket");
            obj.closeWebSocket();
        }


        obj.closeWebSocket = function () {
            if (obj.websocket){
                console.debug("即将主动关闭websocket");
                obj.websocket.close();
            }
        }

        obj.sendMessage = function (message) {
            if (obj.websocket)
                obj.websocket.send(message);
        }
    }
    else {
        showAlert('当前浏览器不支持websocket');
        return;
    }

    return obj;
}
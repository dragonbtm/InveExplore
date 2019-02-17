package com.chain.config;

import io.socket.client.Socket;
import org.java_websocket.client.WebSocketClient;

import javax.websocket.Session;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonDataDefine {

    public static Map<String, Session> wsMgUserMap = new ConcurrentHashMap<>();

    public static Map<String, WebSocketClient> wsMgClientMap = new ConcurrentHashMap<>();

    public static Map<String, Socket> socketIOClientMap = new ConcurrentHashMap<>();


    /**储存localfullnode*/
    public static String localfullnodeUrl = "";

    /**消息数据拉取限制*/
    public static int previewNum = 1;

    public static Long transactionsTimestamp = Instant.now().getEpochSecond();


}

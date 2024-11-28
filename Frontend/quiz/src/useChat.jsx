import { useState, useEffect } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const useChat = () => {
  const [messages, setMessages] = useState([]);
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    // Create a SockJS connection to the WebSocket server
    const sock = new SockJS("http://localhost:8080/ws");
    const client = new Client({
      webSocketFactory: () => sock,
      reconnectDelay: 5000, // Retry connection in case of failure
      onConnect: () => {
        console.log("Connected to WebSocket");

        // Subscribe to the chat topic
        client.subscribe("/topic/chat", (message) => {
          console.log("Received:", JSON.parse(message.body));
          setMessages((prevMessages) => [...prevMessages, JSON.parse(message.body)]);
        });
      },
      onDisconnect: () => console.log("Disconnected from WebSocket"),
      debug: (str) => console.log(str),
    });

    // Activate the STOMP client
    client.activate();
    setStompClient(client);

    // Cleanup the connection on component unmount
    return () => {
      if (client) {
        client.deactivate();
      }
    };
  }, []);

  return { messages: messages || [] }; // Ensure messages is always an array
};

export default useChat;

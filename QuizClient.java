import java.io.*;
import java.net.*;

public class QuizClient {
    public static void main(String[] args) throws Exception {
        String serverMessage;
        String clientAnswer;
        String serverIP = "127.0.0.1"; // 로컬호스트
        int nPort = 6789; // 서버와 동일한 포트 번호 사용
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket(serverIP, nPort);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream())
        );

        // 서버로부터 퀴즈 질문 받기
        serverMessage = inFromServer.readLine();
        System.out.println(serverMessage);

        // 사용자 입력 정답 전송
        System.out.print("Your Answer: ");
        clientAnswer = inFromUser.readLine();
        outToServer.writeBytes(clientAnswer + '\n');

        // 서버로부터 결과 받기
        serverMessage = inFromServer.readLine();
        System.out.println("FROM SERVER: " + serverMessage);

        clientSocket.close(); // 소켓 닫기
    }
}

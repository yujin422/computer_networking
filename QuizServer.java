import java.io.*;
import java.net.*;

public class QuizServer {
    public static void main(String[] args) {
        ServerSocket welcomeSocket = null;
        try {
            String clientAnswer;
            String serverMessage;
            int nPort = 6789; // 서버 포트 번호 설정
            welcomeSocket = new ServerSocket(nPort);
            System.out.println("Server start.. (port#=" + nPort + ")\n");

            while (true) {
                // 새로운 연결 대기
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream())
                );
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

                // 클라이언트에게 퀴즈 질문 보내기
                outToClient.writeBytes("Quiz: What is the capital fo South Korea?\n");

                // 클라이언트의 정답 받기
                clientAnswer = inFromClient.readLine();
                System.out.println("FROM CLIENT: " + clientAnswer);

                // 정답 판별
                if (clientAnswer.equalsIgnoreCase("서울") || clientAnswer.equalsIgnoreCase("Seoul")) {
                    serverMessage = "Correct!\n";
                } else {
                    serverMessage = "Wrong answer. Try again!\n";
                }

                // 결과 클라이언트에게 전송
                outToClient.writeBytes(serverMessage);

                // 클라이언트 소켓 닫기
                connectionSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // 서버 소켓 닫기
            if (welcomeSocket != null) {
                try {
                    welcomeSocket.close();
                    System.out.println("Server socket is closed.");
                } catch (IOException e) {
                    System.out.println("Error closing server socket: " + e.getMessage());
                }
            }
        }
    }
}

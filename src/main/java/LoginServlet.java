import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String FILE_PATH = "/WEB-INF/user_data.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('すべての項目を入力してください！'); window.location.href = 'login.html';</script>");
            return;
        }

        String filePath = getServletContext().getRealPath(FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean loginSuccess = false;
            String fullName = ""; // 用來存放使用者的完整名稱

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(username) && parts[1].equals(password)) {
                    // 登入成功
                    HttpSession session = request.getSession();
                    session.setAttribute("user", username);
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("fullName", parts[2]); // 保存完整名稱
                    fullName = parts[2]; // 獲取完整名稱
                    loginSuccess = true;
                    break;
                }
            }

            if (loginSuccess) {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('ログイン成功！ようこそ, " + fullName + " さん！'); window.location.href = 'index.html';</script>");
            } else {
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>alert('ユーザー名またはパスワードが正しくありません！'); window.location.href = 'login.html';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>alert('サーバーエラーが発生しました！'); window.location.href = 'login.html';</script>");
        }
    }
}
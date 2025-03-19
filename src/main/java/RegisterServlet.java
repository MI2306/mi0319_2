import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final String FILE_PATH = "/WEB-INF/user_data.txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        if (username == null || password == null || fullName == null || email == null ||
            username.isEmpty() || password.isEmpty() || fullName.isEmpty() || email.isEmpty()) {
            response.getWriter().println("<h1>すべての項目を入力してください。</h1>");
            return;
        }

        String filePath = getServletContext().getRealPath(FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(username + "," + password + "," + fullName + "," + email + "\n");
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<script>alert('登録成功！'); window.location.href = 'index.html';</script>");
    }
}
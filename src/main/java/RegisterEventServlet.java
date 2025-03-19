import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerEvent")
public class RegisterEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 參加人數模擬數據
    private static int currentParticipants = 3;
    private static final int maxParticipants = 10;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 確認是否能加入
        if (currentParticipants < maxParticipants) {
            currentParticipants++; // 增加人數
            response.getWriter().write("{\"success\": true, \"currentParticipants\": " + currentParticipants + "}");
        } else {
            response.getWriter().write("{\"success\": false, \"message\": \"定員に達しました！\"}");
        }
    }
}
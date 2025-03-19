
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/estimate")  // 設置 URL 路徑
public class SD extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 取得表單資料
        int numPeople = Integer.parseInt(request.getParameter("numPeople"));
        int hours = Integer.parseInt(request.getParameter("hours"));
        String venueType = request.getParameter("venueType");

        // 根據參數計算估價
        int basePrice;
        switch (venueType) {
            case "small":
                basePrice = 1000;
                break;
            case "medium":
                basePrice = 2000;
                break;
            case "large":
                basePrice = 3000;
                break;
            default:
                basePrice = 0;
                break;
        }

        int estimate = basePrice * hours + (numPeople * 50);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = "{ \"estimate\": " + estimate + " }";
        response.getWriter().write(json);
    }
}
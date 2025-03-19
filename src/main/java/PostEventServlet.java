
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PostEventServlet")
public class PostEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 確認使用者是否已登入
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("ログインが必要です！");
            return;
        }

        // 獲取請求參數
        String eventName = request.getParameter("eventName");
        String eventDate = request.getParameter("eventDate");
        String eventTime = request.getParameter("eventTime");
        String eventType = request.getParameter("eventType");
        String maxParticipants = request.getParameter("maxParticipants");
        String fee = request.getParameter("fee");

        // 檢查參數完整性
        if (eventName == null || eventDate == null || eventTime == null || 
            eventType == null || maxParticipants == null || fee == null ||
            eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || 
            eventType.isEmpty() || maxParticipants.isEmpty() || fee.isEmpty()) {
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("全ての項目を正確に入力してください！");
            return;
        }

        try {
            // 驗證數字格式的參數
            int max = Integer.parseInt(maxParticipants);
            int eventFee = Integer.parseInt(fee);

            if (max <= 0 || eventFee < 0) {
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().write("募集人数と費用は正の値を入力してください！");
                return;
            }

            // 模擬儲存活動數據（此處應寫入到資料庫或檔案中）
            // 比如：將活動詳細信息寫入一個外部文件或資料庫中

            // 返回成功信息
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("イベント「" + eventName + "」が正常に投稿されました！");
        } catch (NumberFormatException e) {
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("募集人数または費用は数値を入力してください！");
        }
    }
}

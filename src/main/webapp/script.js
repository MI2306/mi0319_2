// 切換選單顯示
function toggleMenu() {
    const menuList = document.querySelector('.menu-list');
    menuList.classList.toggle('active');
}

// 輪播功能
let slideIndex = 0;
const slides = document.querySelectorAll('.slide');

function showSlides() {
    slides.forEach((slide, index) => {
        slide.classList.remove('active');
        if (index === slideIndex) {
            slide.classList.add('active');
        }
    });
    slideIndex = (slideIndex + 1) % slides.length;
}

setInterval(showSlides,6000); //6秒更換一次圖片
showSlides(); // 初始化顯示第一張圖片


//滾動動畫
document.addEventListener("DOMContentLoaded", function () {
    const elements = document.querySelectorAll(".fade-in");

    function handleScroll() {
        elements.forEach(el => {
            const rect = el.getBoundingClientRect();
            if (rect.top < window.innerHeight - 100) {
                el.classList.add("show");
            }
        });
    }

    window.addEventListener("scroll", handleScroll);
    handleScroll(); // 頁面載入時先檢查一次
});

//BACK TO TOP
const backToTop = document.getElementById("backToTop");

window.addEventListener("scroll", () => {
    if (window.scrollY > 300) {  // 滾動超過 300px 才顯示
        backToTop.classList.add("show");
    } else {
        backToTop.classList.remove("show");
    }
});

backToTop.addEventListener("click", () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
});


/* ABOUT */

if (localStorage.getItem("darkMode") === "true") {
    document.body.classList.add("dark-mode");
}

// 開啟 Lightbox，確保行動版圖片不超過畫面範圍
function openLightbox(src) {
    const lightbox = document.getElementById("lightbox");
    const img = document.getElementById("lightbox-img");

    img.src = src;
    lightbox.style.display = "flex";
}

// 關閉 Lightbox
function closeLightbox() {
    document.getElementById("lightbox").style.display = "none";
}

// 綁定所有 zoomable 圖片，點擊可放大
document.addEventListener("DOMContentLoaded", function () {
    const images = document.querySelectorAll(".zoomable-image");

    images.forEach(img => {
        img.addEventListener("click", function () {
            openLightbox(this.src);
        });
    });
    // 防止長按時彈出預設選單（行動裝置）
    document.addEventListener("contextmenu", function (event) {
        if (event.target.classList.contains("zoomable-image")) {
            event.preventDefault(); // 阻止長按選單
        }
    });
    
});


/*---- rental ----- */

function getEstimate() {
    // 捕獲輸入值
    const numPeople = parseInt(document.getElementById("numPeople").value) || 0; // 人數
    const hours = parseInt(document.getElementById("hours").value) || 0; // 時數
    const venues = document.querySelectorAll("input[name^='venue']:checked"); // 選擇的樓層
    const extras = document.querySelectorAll("input[name^='extra']:checked"); // 選擇的追加服務

    // 基本費用與規則
    const basicRate = 3000; // 每小時基本費用
    const venueAdditionalRate = 1500; // 每多一層追加費用
    const extraRatePerPerson = 200; // 每人每項追加服務費用

    // 驗證輸入
    if (venues.length === 0) {
        alert("少なくとも1つのフロアを選択してください！");
        return;
    }

    if (numPeople <= 0) {
        alert("人数を入力してください！");
        return;
    }

    if (numPeople <= 0) {
        alert("予約時間を入力してください！");
        return;
    }

    // 計算樓層費用
    const venueCost = (venues.length - 1) * venueAdditionalRate;

    // 計算追加服務費用 (不乘以時數)
    const extraCost = extras.length * extraRatePerPerson * numPeople;

    // 總金額計算
    const totalCost = (basicRate * hours) + venueCost * hours + extraCost;

    // 平均金額（小數點捨去）
    const averageCost = numPeople > 0 ? Math.floor(totalCost / numPeople) : 0;

    // 驗證結果是否有效
    if (isNaN(totalCost) || totalCost <= 0) {
        alert("計算結果が無効です。入力値を確認してください。");
        return;
    }

    // 更新結果區域
    document.getElementById("selected-numPeople").textContent = numPeople || "未入力";
    document.getElementById("selected-hours").textContent = hours || "未入力";
    document.getElementById("selected-venue").textContent = Array.from(venues).map(v => v.parentElement.textContent.trim()).join("、") || "-";
    document.getElementById("selected-extras").textContent = Array.from(extras).map(e => e.parentElement.textContent.trim()).join("、") || "-";

    // 更新每人平均金額
    const averageElement = document.getElementById("selected-average");
    if (averageElement) {
        averageElement.textContent = averageCost ? `¥${averageCost.toLocaleString()}` : "-";
    }

    // 更新見積り結果
    const estimateResultElement = document.getElementById("estimate-result");
    if (estimateResultElement) {
        estimateResultElement.textContent = totalCost ? `総金額: ¥${totalCost.toLocaleString()}` : "計算エラー";
    }
    
}
//clear form
function resetForm() {
    document.getElementById("estimateForm").reset(); // 重置表單
}


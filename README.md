# 線上投票系統OnlineVotingSystem
這是一個基於 Spring Boot 3 與 Vue 3 (Vite) 開發的全端投票系統。

## 使用技術

###### 資料庫
H2 記憶體資料庫

###### 後端 (Backend)
Java 21

Spring Boot 3.x

Spring Data JPA 

Maven

###### 前端 (Frontend)
Vue 3 (Composition API)

TypeScript

Vite

Vue Router 4

Axios

## 如何在本地運行
###### 後端啟動 (Spring Boot)
執行方式：
1.開啟 backend 資料夾。
2.執行 OnlineVotingSystemApplication.java 中的 main 方法。

註：若您的terminal環境變數指向 JRE 而導致 ./mvnw 報錯，請務必使用 IDE 內建的 Run 按鈕啟動。

後端預設運行於：http://localhost:8080

###### 前端啟動 (Vue 3)
執行方式:

1.開啟終端機進入 frontend 資料夾。
2.執行安裝與啟動指令：

Bash
npm install
npm run dev
前端預設網址：http://localhost:5173

## 專案結構
Plaintext
.
├── DB/
│   ├── data.sql            # 寫入預設資料
│   └── schema.sql          # 建立資料庫
├── backend/                # Spring Boot 專案根目錄
│   ├── src/main/java       # Java 原始碼
│   └── pom.xml             # Maven 配置
├── frontend/               # Vue 專案根目錄
│   ├── src/
│   │   ├── views/          # 頁面組件 (VoteView, AdminView)
│   │   ├── router/         # Vue Router 配置
│   │   └── api/            # Axios 封裝與 API 定義
│   └── vite.config.ts      # Vite 配置
└── README.md
## 關鍵問題解決紀錄 (Dev Log)
在開發過程中，我針對以下技術問題進行了優化處理與Debug：

* API 封裝與路徑優化 (路由一致性)：

    * 問題描述：引入 Vue Router 後，因頁面跳轉導致相對路徑 `./api` 解析出錯。

    * 解決方案：將 API 請求模組化至 `src/api/` 下，建立統一的 Axios 實例並設定 `baseURL`。這不僅解決了路徑偏移問題，也提升了程式碼的可維護性，方便未來統一處理攔截器與環境變數。

* 解決 POST 500 內部伺服器錯誤 (資料格式匹配問題)：

    * 問題描述：在實作「新增項目」功能時，前端呼叫 API 出現 500 錯誤。經查是因為後端使用 `@RequestBody VoteItem` 接收資料，但前端僅傳送了純文字字串。

    * 解決方案：將前端 Axios 請求調整為物件格式 `{ name: itemName }`，並確保後端實體類別 (Entity) 具備 無參數建構子 (Default Constructor)，使 Jackson 能夠正確執行 JSON 與 Java 物件的對應 (Mapping)。

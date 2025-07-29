学生管理システム（Student Management System）
概要
Spring BootとMySQLを使用して構築した学生情報管理システムです。 学生データとコース情報を管理し、条件に応じたデータ抽出機能を実装しています。
使用技術

バックエンド: Spring Boot 3.3.5
データベース: MySQL 8.0
ORM: MyBatis
言語: Java 21
開発環境: IntelliJ IDEA
バージョン管理: Git / GitHub

実装機能
1. 基本的なデータ操作

学生情報の取得
コース情報の取得
REST API による JSON レスポンス

2. 条件検索機能

年齢条件検索: 30代の学生のみを抽出
コース条件検索: 特定のコース（Java）の受講者情報を抽出

3. データベース設計

学生テーブル（students）
コーステーブル（students_courses）
適切なリレーションシップ設計

API エンドポイント
エンドポイント機能説明/studentList全学生取得全学生の基本情報を返す/studentsCourseList全コース取得全コース情報を返す/students/thirties30代学生取得年齢が30-39歳の学生のみを抽出/students-courses/javaJavaコース取得Javaコースの受講情報のみを抽出
データベースセットアップ

### Javaコースの抽出結果
```json
[
  {
    "id": 1,
    "studentId": 1,
    "courseName": "Java",
    "courseStartAt": "2024-01-01T00:00:00",
    "courseEndAt": "2024-06-30T23:59:59"
  },
  {
    "id": 2,
    "studentId": 2,
    "courseName": "Java",
    "courseStartAt": "2024-02-01T00:00:00",
    "courseEndAt": "2024-07-31T23:59:59"
  },
  {
    "id": 4,
    "studentId": 4,
    "courseName": "Java",
    "courseStartAt": "2024-04-01T00:00:00",
    "courseEndAt": "2024-09-30T23:59:59"
  }
]
30代学生の抽出結果
json[
  {
    "id": 2,
    "name": "佐藤花子",
    "kanaName": "サトウハナコ",
    "nickname": "はなちゃん",
    "email": "hana@example.com",
    "area": "大阪",
    "age": 32,
    "sex": "女"
  },
  {
    "id": 3,
    "name": "山田次郎",
    "kanaName": "ヤマダジロウ",
    "nickname": "じろう",
    "email": "jiro@example.com",
    "area": "愛知",
    "age": 38,
    "sex": "男"
  }
]
```sql
-- データベース作成
CREATE DATABASE student_management;
USE student_management;

-- 学生テーブル作成
CREATE TABLE students (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(50) NOT NULL,
    kana_name VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) DEFAULT NULL,
    email VARCHAR(50) NOT NULL,
    area VARCHAR(50) DEFAULT NULL,
    age INT DEFAULT NULL,
    sex VARCHAR(10) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- コーステーブル作成
CREATE TABLE students_courses (
    id VARCHAR(36) NOT NULL,
    student_id VARCHAR(36) NOT NULL,
    course_name VARCHAR(50) NOT NULL,
    course_start_at TIMESTAMP NULL DEFAULT NULL,
    course_end_at TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- サンプルデータの確認方法
-- 全学生を確認
SELECT * FROM students;

-- 全コースを確認
SELECT * FROM students_courses;

-- 30代の学生を確認
SELECT * FROM students WHERE age BETWEEN 30 AND 39;

-- Javaコースの受講者を確認
SELECT s.*, sc.course_name 
FROM students s 
JOIN students_courses sc ON s.id = sc.student_id 
WHERE sc.course_name = 'Java';
設定ファイル
application.properties
properties# データベース接続設定
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# MyBatis設定
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.example.studentmanagementsystem.entity
実行方法

MySQLサーバーを起動
データベースを作成
bash# MySQLにログイン
mysql -u root -p

# 上記のSQLを実行してデータベースとテーブルを作成

アプリケーションを実行
bash# プロジェクトディレクトリに移動
cd student-management-system

# アプリケーション実行
./mvnw spring-boot:run

動作確認

ブラウザで http://localhost:8080/studentList にアクセス
各エンドポイントをテスト



動作確認結果
全学生一覧の取得結果
json[
  {
    "id": 1,
    "name": "田中太郎",
    "kanaName": "タナカタロウ",
    "nickname": "たろう",
    "email": "taro@example.com",
    "area": "東京",
    "age": 25,
    "sex": "男"
  },
  {
    "id": 2,
    "name": "佐藤花子",
    "kanaName": "サトウハナコ",
    "nickname": "はなちゃん",
    "email": "hana@example.com",
    "area": "大阪",
    "age": 32,
    "sex": "女"
  },
  {
    "id": 3,
    "name": "山田次郎",
    "kanaName": "ヤマダジロウ",
    "nickname": "じろう",
    "email": "jiro@example.com",
    "area": "愛知",
    "age": 38,
    "sex": "男"
  },
  {
    "id": 4,
    "name": "鈴木三郎",
    "kanaName": "スズキサブロウ",
    "nickname": "さぶちゃん",
    "email": "saburo@example.com",
    "area": "福岡",
    "age": 28,
    "sex": "男"
  }
]
Javaコースの抽出結果
json[
  {
    "studentId": 1,
    "studentName": "田中太郎",
    "courseName": "Java"
  },
  {
    "studentId": 2,
    "studentName": "佐藤花子",
    "courseName": "Java"
  }
]
開発で身につけた技術
バックエンド開発

Spring Boot による Web アプリケーション開発
REST API の設計と実装
MyBatis を使用したデータベース操作

データベース操作

MySQL でのテーブル設計
SQL を使用した条件検索
データベース接続設定

問題解決能力

データベース接続エラーの解決
SQL 構文エラーの修正
カラム名の命名規則対応（snake_case ↔ camelCase）

開発環境構築

IntelliJ IDEA でのプロジェクト管理
MySQL サーバーの設定
Git を使用したバージョン管理

今後の拡張予定

フロントエンド画面の実装
学生情報の登録・更新・削除機能
より複雑な検索条件の追加
ユーザー認証機能

学習内容
このプロジェクトを通じて、以下の技術を実践的に学びました：

Spring Boot フレームワークの基本
データベース連携アプリケーションの開発
REST API の設計思想
エラーハンドリングとデバッグ手法

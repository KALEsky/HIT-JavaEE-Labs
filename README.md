# HIT-JavaEE-Labs

Java EE 课程实验与分组大作业归档，覆盖 Java 基础文件操作、JDBC 数据访问、Servlet/JSP Web 应用和学生信息管理系统。

这是一个基于本科课程作业整理的历史实验仓库，重点保留源码结构、实验任务和可复现的技术脉络。代码年代较早，部分依赖和 API 已经过时，不应直接视为生产级 Web 应用。

## Overview

仓库中的实验逐步展示了从 Java 基础 I/O 到传统 Java Web 三层结构的学习过程：

1. 使用 Java 类和回调接口完成文件复制/目录操作。
2. 通过 DAO、实体类和 JDBC 访问 MySQL。
3. 使用 Servlet/JSP 实现登录、Cookie/Session 和学生信息增删改查。
4. 在分组大作业中扩展到学生、教师和成绩等多实体管理。

## Contents

| Module | Main content | Archive status |
| --- | --- | --- |
| `labs/lab-01` | UML/design artifact for the first experiment | Historical design material |
| `labs/lab-02` | Java callback, console and file-copy utilities | Source included |
| `labs/lab-03` | JDBC connection, DAO and student entity examples | Source included |
| `labs/lab-04` | Servlet/JSP student management web application | Source and web resources included |
| `group-project` | Team student/teacher management application | Team coursework source |
| `docs/assignments` | Historical experiment and project task descriptions | Archive reference only |

## Features

- Java file and directory operations through callback-based utilities.
- JDBC connection and basic database access through DAO-style classes.
- Servlet endpoints for login and student management.
- JSP pages for listing, adding, modifying and removing records.
- Session and Cookie-based login state used by the original coursework.
- Team project extension with student and teacher entities.

## Tech Stack

- Java
- Servlet / JSP
- JDBC
- MySQL
- Eclipse-style legacy Web project layout
- Apache Tomcat-compatible servlet container

## Project Structure

```text
 labs/
 ├── lab-01/                 # UML/design material
 ├── lab-02/src/             # Java utilities and callback examples
 ├── lab-03/src/             # JDBC + DAO example
 └── lab-04/
     ├── src/                 # Servlet, DAO and entity classes
     └── webapp/              # JSP/HTML resources
 group-project/
 ├── src/java/                # Team Java source
 └── src/webapp/              # Team Web resources
 docs/assignments/            # Historical task descriptions
```

## Environment

原项目使用的是较早的 Java Web 工具链。建议使用：

- JDK 8
- MySQL 5.x/兼容版本
- Eclipse 或 IntelliJ IDEA 的传统 Web 项目支持
- Apache Tomcat 8/9 级别的 Servlet 容器

当前仓库没有绑定特定 IDE、Tomcat 或 SDK 的本机路径。

## Database Configuration

实验 3、实验 4 和分组项目中的数据库连接代码使用环境变量占位符：

```text
JAVAEE_DB_URL=jdbc:mysql://127.0.0.1:3306/hit
JAVAEE_DB_USER=root
JAVAEE_DB_PASSWORD=<local-password>
```

相关代码位置包括：

- `labs/lab-03/src/cn/edu/hit/utils/DbUtils.java`
- `labs/lab-04/src/cn/edu/hit/utils/DbUtils.java`
- `group-project/src/java/cn/edu/hit/utils/DBUtils.java`

请使用本地测试数据库，不要提交真实密码、远程数据库地址或个人数据。

## Usage

这些工程没有统一的 Maven/Gradle 构建脚本，推荐分别导入对应实验目录：

1. 在 IDE 中导入 Java source folder。
2. 为 JDBC 实验配置 MySQL Connector/J。
3. 为 Servlet/JSP 实验配置 Tomcat。
4. 配置上述数据库环境变量。
5. 创建与代码 SQL 查询相匹配的测试表。
6. 启动 Web 应用并访问登录页或学生管理页。

## Historical Course Materials

`docs/assignments` 中的任务文件来自过往课程，仅用于个人学习回顾、课程内容整理和历史归档，不用于当前课程提交、考试或任何形式的抄袭。

## Team Coursework

`group-project` 是小组共同完成的课程项目。仓库使用中性的目录名，不把某一位组员的学号或姓名当作项目名称；后续如公开展示，应在项目说明中补充经过确认的协作信息。

## Known Limitations

- 代码使用旧版 JDBC 驱动、Servlet/JSP 和 Eclipse 工程结构。
- 部分 SQL 使用字符串拼接，存在 SQL 注入风险，不应直接用于生产环境。
- 原始代码中的 Cookie 密码保存和明文数据库认证逻辑仅作为历史实现保留。
- 当前仓库未包含个人提交版实验报告，以避免把个人身份信息和提交格式带入公开代码仓库。
- 运行环境和数据库 schema 需要根据每个实验的历史要求自行准备。

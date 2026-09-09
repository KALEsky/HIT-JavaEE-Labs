# HIT-JavaEE-Labs

这是 Java EE 课程实验和分组大作业的完整记录。实验从 Java 的回调接口、文件复制开始，接着进入 JDBC 和 DAO，最后落到 Servlet/JSP 的登录、Cookie/Session 和学生信息管理。代码年代比较早，但学习路线很完整：先把 Java 本身用起来，再让它连接数据库，最后把请求、业务和页面接成一个 Web 应用。

我没有把这些实验重新拼成一个“现代 Spring 项目”。那样看起来可能更整齐，却会把每个实验当时想练的东西抹掉。现在的目录按课程实验拆开，方便分别阅读，也能看到从小例子到分组项目的变化。

## 课程路线

| 部分 | 主要问题 | 代码入口 |
| --- | --- | --- |
| Lab 1 | 用 UML 表达一个小系统的对象关系 | `labs/lab-01/Work1.uml` |
| Lab 2 | 如何把“做什么”和“怎么遍历/复制”分开 | `labs/lab-02/src/` |
| Lab 3 | Java 程序怎样建立 JDBC 连接并读写实体 | `labs/lab-03/src/` |
| Lab 4 | 请求、Cookie/Session、DAO 和 JSP 怎样协作 | `labs/lab-04/` |
| Group project | 把学生管理扩展到学生、教师和页面操作 | `group-project/` |

## Lab 2：回调、控制台和文件复制

`CallBack.java` 定义操作完成后的回调接口，`Console.java` 提供一个控制台实现，`FileUtils.java` 负责文件/目录处理，`Xcopy.java` 则把这些部件组合成类似 `xcopy` 的复制工具。

这个实验的重点不是复制文件本身，而是把“遍历文件”和“遍历后做什么”拆开：文件工具负责发现路径，回调对象负责决定输出或处理方式。对于刚开始写 Java 的阶段，这是比直接在一个 `main()` 里堆所有逻辑更重要的抽象。

```bash
javac labs/lab-02/src/*.java
java -cp labs/lab-02/src Xcopy
```

具体参数需要结合源码里的命令行读取方式；实验报告和任务书放在 `docs/` 里，里面有当时的调用示例。

## Lab 3：JDBC 和 DAO

Lab 3 从 `HelloDB.java` 开始，展示最直接的数据库连接；`DbUtils.java` 负责连接和关闭，`Student.java` 是实体，`StudentDao.java` 负责查询。这里已经能看到一个很典型的分层雏形：页面或入口不直接处理 ResultSet，而是由 DAO 把数据库记录转成 Java 对象。

```text
HelloDB / 调用方
        │
        ▼
StudentDao  ──>  DbUtils  ──>  MySQL
        │
        ▼
Student entity
```

实验源码使用 JDBC，需要在 classpath 中加入 MySQL Connector/J，并准备与 SQL 语句相符的测试表。当前仓库不携带真实数据库，也没有把驱动 JAR 直接塞进源码目录。

## Lab 4：Servlet/JSP 学生管理

Lab 4 是第一个真正有 Web 应用形状的实验。`LoginServlet` 接收登录请求，`LoginDao` 查询用户，`StudentServlet` 处理学生列表和增删改，JSP 页面负责表单与结果展示。登录状态通过 `HttpSession` 传递，勾选“记住我”时还会写 Cookie。

这个实验最值得回看的地方，是一次请求会经过哪些边界：表单参数进入 Servlet，Servlet 判断会话，再调用 DAO，DAO 执行 SQL，最后 JSP 读取结果并渲染页面。它当然没有现代 Web 框架的路由、模板和依赖注入，但“请求—业务—数据—页面”的链路已经完整了。

## 分组大作业

`group-project/` 在 Lab 4 的基础上继续扩展：除了学生信息，还加入教师实体、登录入口、学生列表、新增和修改页面。目录保持了传统 Java Web 项目的布局：

```text
group-project/
├── src/java/cn/edu/hit/
│   ├── controller/       # LoginServlet / StudentServlet
│   ├── dao/              # LoginDao / StudentDao
│   ├── entity/           # Student / Teacher
│   └── utils/            # DBUtils
└── src/webapp/
    ├── login.html / login.jsp
    ├── stulist.jsp
    ├── addStu.html
    ├── addTea.html
    └── modifyStu.jsp
```

这是小组共同完成的课程项目，所以报告中会出现组员姓名和学号等协作记录；它们属于当时的项目背景，不代表所有代码都是个人独立完成。仓库目录使用中性名称，没有把某一位组员的身份信息放到项目名里。

## 数据库配置

实验 3、实验 4 和分组项目的连接代码已经整理为环境变量占位符。对应的代码文件是：

- `labs/lab-03/src/cn/edu/hit/utils/DbUtils.java`
- `labs/lab-04/src/cn/edu/hit/utils/DbUtils.java`
- `group-project/src/java/cn/edu/hit/utils/DBUtils.java`

本地运行时准备：

```text
JAVAEE_DB_URL=jdbc:mysql://127.0.0.1:3306/hit
JAVAEE_DB_USER=root
JAVAEE_DB_PASSWORD=<local-password>
```

不同实验使用的表名和字段并不完全相同，不能只创建一套表就期待所有页面都能工作。建议先看对应实验的 DAO SQL，再创建最小测试 schema。真实密码、远程数据库地址和个人数据不应放进仓库。

## 如何运行

这些工程没有统一的 Maven/Gradle 构建脚本，建议按实验导入：

1. Lab 2 直接把 `src/` 作为 Java source folder 编译。
2. Lab 3 配置 MySQL Connector/J，先测试 `HelloDB`，再运行 DAO 查询。
3. Lab 4 和分组项目导入 Eclipse/IDEA 的传统 Web 工程，配置 Servlet API、JDBC 驱动和 Tomcat。
4. 设置上面的数据库环境变量，准备与源码 SQL 一致的表。
5. 启动 Tomcat 后，从登录页进入学生列表，再尝试新增、修改和删除流程。

如果只是阅读源码，不需要安装 Tomcat；从 `controller`、`dao` 和 `webapp` 三个目录顺着请求走一遍，已经能看懂大部分设计。

## 这里有哪些真实的“不完美”

- 旧代码里有字符串拼接 SQL，存在 SQL 注入风险；它们是课程阶段的实现，不应直接用于生产。
- 登录逻辑保留了把密码放进 Cookie 的旧写法，这是当时实验的一部分，现代应用应改为安全的会话/token 方案。
- 部分页面和注释受旧编码影响，浏览器或终端里可能显示乱码。
- 没有现代单元测试、数据库迁移脚本或持续集成配置。
- Servlet/JSP、Tomcat 和 JDBC 驱动版本需要按旧工程的依赖关系匹配。

## 资料与报告

`docs/assignments/` 保存各次实验和分组大作业的任务书，`docs/reports/` 保存实验报告和分组项目报告。报告是这个仓库很重要的一部分：它们记录了当时的页面、数据库设计、调试结果和小组协作过程。所有课程材料仅用于个人学习回顾、理解实验和保存作品，不用于当前课程提交、考试或抄袭。

## 我现在回头看

这组实验最清楚地展示了 Java Web 学习中的一个转折：刚开始关注的是“怎么让文件复制成功”，后来开始关心“一个请求经过哪些层、数据在哪一层转换、状态应该放在哪里”。即使今天不会再用同样的 JSP 页面写新系统，这些实验仍然是很具体的工程记忆：数据库连接不是凭空出现的，Session 也不是登录按钮按下之后自动存在的，每一层都要有人负责。

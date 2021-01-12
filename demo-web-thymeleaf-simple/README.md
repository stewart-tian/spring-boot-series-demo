# spring-boot-demo-web-thymeleaf

> demo-web-thymeleaf-simple : spring-boot web thymeleaf简单使用

## 一、基础配置

### 1.1、引入依赖

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

### 1.2、 提供启动类和控制器

控制器

    @Controller
    @RequestMapping("/thymeleaf")
    public class ThymeleafController {
    
        @GetMapping("/hello")
        public String hello(ModelMap modelMap) {
            modelMap.addAttribute("welcome", "Hello Spring boot");
            return "hello";
        }
    
        @GetMapping("/str")
        public String str(ModelMap modelMap) {
            modelMap.addAttribute("developer", "java");
            return "str";
        }
    
        @GetMapping("/list")
        public String list(ModelMap modelMap) {
            List<User> list = new ArrayList<>();
            list.add(new User(1L, "tian", 28, "man"));
            list.add(new User(2L, "java", 20, "man"));
            list.add(new User(3L, "xie", 26, "woman"));
            modelMap.addAttribute("userList", list);
            return "list";
        }
    
    }

### 1.3、添加对应h5页面

h5页面中需要对html标签添加 xmlns:th="http://www.thymeleaf.org"

hello.html

    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Hello</title>
    </head>
    <body>
    <p th:text="${welcome}"></p>
    </body>
    </html>

str.html

    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>字符串</title>
    </head>
    <body>
    <div>
        <p th:text="${developer}"></p>
    </div>
    </body>
    </html>

list.html

    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>User List</title>
    </head>
    <body>
    <div>
        <table>
            <tr th:each="user,item : ${userList}">
                <td th:text="${user.id}"></td>
                <td th:text="${user.name}"></td>
                <td th:if="${user.name == 'tian'}" th:text="田"></td>
                <td th:if="${user.name == 'java'}" th:text="coder"></td>
                <td th:unless="${user.name != 'xie'}" th:text="爱"></td>
                <td th:text="${user.age}"></td>
                <td th:text="${user.age gt 26 ? '不小了' : '正年轻' }"></td>
                <td th:switch="${user.sex}">
                    <span th:case="'women'">美</span>
                    <span th:case="'man'">帅</span>
                    <span th:case="*">无</span>
                </td>
                <td th:text="${item.index}"></td>
            </tr>
        </table>
    </div>
    </body>
    </html>

### 1.4 访问接口验证

    http://localhost:10201/thymeleaf/hello
    http://localhost:10201/thymeleaf/str
    http://localhost:10201/thymeleaf/list
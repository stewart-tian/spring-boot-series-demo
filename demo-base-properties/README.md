# spring-boot-demo-base-properties

> demo-base-properties : spring-boot 基础 引入配置参数

## 一、引入配置的多种方式

### 1.1、注入全局配置参数

1.1.1、全局配置application.yml中添加属性配置

    project:
        name: demo-properties

1.1.2、对需要使用该配置的参数通过@Value进行注入

    @Value("${project.name}")
    private String projectName;

1.1.3、验证注入

    @GetMapping("/prop")
    public String projectProperties(){
        System.out.println(projectName);
        return projectName;
    }

### 1.2、通过统一前缀注入配置类中

1.2.1、配置文件中添加统一前缀配置参数

    stewart:
      project:
        info: demo-project
        port: 10002

1.2.2、配置参数类，通过@ConfigurationProperties(prefix = "stewart.project")指明配置前缀， 通过@Configuration让spring容器加载并通过Setting注入属性值

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "stewart.project")
    public class PrefixProperties {
        private String info;
        private Integer port;
    }

1.2.3、验证注入

    @GetMapping("/prop/prefix")
    public String importPropertiesByPrefix(){
        System.out.println(prefixProperties);
        return prefixProperties.toString();
    }

### 1.3、引入指定配置文件中的配置

1.3.1、创建配置文件config.properties

    config.params.str=String
    config.params.num=1234
    config.params.flag=true
    config.params.list=java,spring,mysql
    config.params.map.key1=value1
    config.params.map.key2=value2

1.3.2、通过@PropertySource("classpath:config.properties")指定配置文件

    @Data
    @Configuration
    @PropertySource("classpath:config.properties")
    @ConfigurationProperties(prefix = "config.params")
    public class ConfigProperties {
    
        private String str;
    
        private Integer num;
    
        private Boolean flag;
    
        private List<String> list;
    
        private Map<String, Object> map;
    
    }

1.3.3、验证配置

    @GetMapping("/prop/file")
    public String otherFileProperties(){
        System.out.println(configProperties);
        return configProperties.toString();
    }

## 1.4、通过指定yaml文件引入配置

1.4.1、创建配置文件config.yml

    config:
      props:
        str: String
        num: 1234
        flag: true
        list:
          - java
          - spring
          - mysql
        map: { key1: value1, key2: value2 }

1.4.2、spring-boot中@PropertySource，默认不支持yml解析文件，需自己添加对yaml配置文件解析工厂类

    public class CustomPropertySourceFactory extends DefaultPropertySourceFactory {

        @Override
        public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
            String sourceName = (name == null) ? resource.getResource().getFilename() : name;
            assert sourceName != null;
            if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
                YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
                factory.setResources(resource.getResource());
                factory.afterPropertiesSet();
                Properties properties = factory.getObject();
                assert properties != null;
                return new PropertiesPropertySource(sourceName, properties);
            }
            return super.createPropertySource(name, resource);
        }

    }

1.4.3、配置文件添加指定解析工厂

    @Data
    @Configuration
    @PropertySource(value = "classpath:config.yml", factory = CustomPropertySourceFactory.class)
    @ConfigurationProperties(prefix = "config.props")
    public class ConfigYaml {
    
        private String str;
    
        private Integer num;
    
        private Boolean flag;
    
        private List<String> list;
    
        private Map<String, Object> map;
    
    }

1.4.4、验证配置

    @GetMapping("/prop/yaml")
    public String yamlFileProperties(){
        System.out.println(configYaml);
        return configYaml.toString();
    }

## 二、不同环境的配置

spring通过spring.profiles.active配置值来指定对应的配置文件,最终构建的配置文件格式 application[-配置值].yml|application[-配置值].properties

2.1.1、添加多环境的配置文件

application-dev.yml

    stewart:
      project:
        info: dev-project

application-prod.yml

    stewart:
      project:
        info: prod-project

2.1.2、全局配置文件中指定配置环境值

    spring:
      profiles:
        active: prod

2.1.3、添加注入参数

    @Value("${stewart.project.info}")
    private String envProjectName;

2.1.4、验证

    @GetMapping("/prop/env")
    public String envProperties() {
        System.out.println(envProjectName);
        return envProjectName;
    }

2.1.4.1、更改application.yml中的spring.profiles.active为dev，或通过启动时添加启动参数指定spring.profiles.active为dev，再次验证。

PS:启动参数优先级高于全局配置文件application.yml
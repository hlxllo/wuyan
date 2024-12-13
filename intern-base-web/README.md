# 如何运行STS临时访问凭证示例工程
1.根据注释修改AStsController.java和index.html中的变量。

2.运行示例工程。
- 在本地运行时，执行：

```shell
mvn compile exec:java -Dexec.mainClass="vip.xiaozhao.intern.baseUtil.NaofferWebApplication"
```

- 在指定了实例（或节点）角色的ECS（或ECI、容器服务）上运行时，执行：

```shell
ALIBABA_CLOUD_ECS_METADATA=<your_instance_ram_role> mvn compile exec:java -Dexec.mainClass="Application"
```
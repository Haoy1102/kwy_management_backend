# 物料&客户关系管理系统的后端
- 支持原材料管理、商品管理、订单管理、客户管理、对账/打印/导出等功能。
- 支持自由的订单发货(部分发货)与回款/对账(部分交付/对账)处理

## 前端链接
- https://github.com/Haoy1102/kwy_management_front
## 主要技术
SpringBoot、MySQL、Mybatis-Plus
## 配置文件
请在此处修改个人的连接信息
- secret.properties
## docker部署
构建命令
```
sudo chmod +x build.sh
./build.sh
```
如需修改docker镜像中启动的环境(dev/staging/prod)，
请修改docker-compose-app.yml中的属性
```
SPRING_PROFILES_ACTIVE
```
或: 在Dockerfile.yml中增加环境变量
```
ENV spring.profiles.active=staging
```
在前后端镜像构建完成后，通过docker-compose-app.yml进行启动，启动命令：
```
docker-compose -f docker-compose-app.yml up -d
```
# 功能说明&展示
### 客户信息
记录客户信息，快速生成订单。
- 关联订单&回款信息，用于快速查看和对账。

客户信息速览

![customer-overview.png](doc%2Fimg%2Fcustomer-overview.png)

对账单-期内已发货品总单

![check-form1.png](doc%2Fimg%2Fcheck-form1.png)

对账单-汇款记录

![check-form2.png](doc%2Fimg%2Fcheck-form2.png)

### 订单管理
订单用于记录客户的一次提货要求(该类行业普遍先用后付，因此回款信息汇总/追踪过程复杂困难)
- 订单中包含多个需要的商品项，支持分批的发货
- 订单中一个需求的商品项可以由多个批次的商品提供(订单商品项与商品批次为1:n关系，两批同类商品可用于一次发货)
- 支持打印订单票据，导出订单数据
- 关联客户信息，用于统计对账。

订单状态：商品全部未发货

![order-1.png](doc%2Fimg%2Forder-1.png)

订单状态：商品全部发货等待回款

![order-2.png](doc%2Fimg%2Forder-2.png)

订单状态：订单完成

![order-3.png](doc%2Fimg%2Forder-3.png)

打印票据

![order-print.png](doc%2Fimg%2Forder-print.png)

其余状态不再举例

### 生产商品总览
商品以批次进行管理，(商品类型，商品批次)形成一个商品sku
- 通过生产生成
- 通过订单发货消耗、管理员手动校对消耗(存在非出售损耗现象)
- 每个批次的商品消耗操作会被详细记录
- 支持查询操作

商品总览

![product-overview.png](doc%2Fimg%2Fproduct-overview.png)

商品管理：详细记录商品sku的数量动向

![product-manage.png](doc%2Fimg%2Fproduct-manage.png)

### 原料管理
原料分为不同的供货商，(原料种类，供货商)形成一个货品sku。
- 通过采购生成
- 通过仓库管理员手动出库消耗(用于生产用途或其他原因损耗)
- 支持采购信息的记录
- 支持查询操作

原料总览

![material-overview.png](doc%2Fimg%2Fmaterial-overview.png)

库存管理

![goods.png](doc%2Fimg%2Fgoods.png)

采购管理

![goods-buy.png](doc%2Fimg%2Fgoods-buy.png)
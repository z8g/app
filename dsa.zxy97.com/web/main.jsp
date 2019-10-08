<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#kaisa" data-toggle="collapse">凯撒加密</a>
            </div>
            <div class="panel-body collapse" id="kaisa">
                <ul class="list-group">
                    <li class="list-group-item">为大型非结构化数据设计的新的 <span class="label label-default">存储和处理技术</span></li>
                    <li class="list-group-item">并行处理</li>
                    <li class="list-group-item">群集</li>
                    <li class="list-group-item">大型网络环境</li>
                    <li class="list-group-item">高连通性和高吞吐量</li>
                    <li class="list-group-item">云计算和横向扩展架构</li>
                </ul>						
            </div>
        </div>
    </div>
</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#mongodb" data-toggle="collapse">Mongo数据库</a>
            </div>
            <div class="panel-body collapse" id="mongodb">
                <p>Mongo数据库便是这种 <span class="label label-default">存储和处理技术</span> 之一</p>
                <p class="well">Mongo数据库基于<code>文档</code>，数据模型使用<code>BSON</code>(二进制JSON)，标识符是<code>_code</code></p>

            </div>
        </div>
    </div>
</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#anzhuang" data-toggle="collapse">安装MongoDB</a>
            </div>
            <div class="panel-body collapse" id="anzhuang">
                <blockquote>
                    <p>Windows安装MongoDB：</p>
                    <p>下载地址：<a href="https://www.mongodb.com/">https://www.mongodb.com/</a></p>
                    <p>选择版本：<span>4.0.3</span></p>
                    <p>安装包大小：187MB</p>
                    <p>笔者的安装位置：<code>C:\Program Files\MongoDB</code></p>
                    <p>笔者提示：如果感觉太慢可以使用笔者提供的64位下载地址 <a href="http://cdn.zxy97.com/s/mongodb4.0.3.msi">mongodb4.0.3.msi</a></p>
                </blockquote>
                <blockquote>
                    <p>Linux安装MongoDB：</p>
                    <p>下载安装包后解压即可：<code>tar -xvf mongodb-linux-x86-x.x.x.tgz</code></p>
                </blockquote>
            </div>
        </div>
    </div>
</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#yanzheng" data-toggle="collapse">验证是否安装成功</a>
            </div>
            <div class="panel-body collapse" id="yanzheng">
                <blockquote>
                    <p>一、在<code>C:\Program Files\MongoDB\Server\4.0\bin</code>目录按 <kbd>Shift</kbd> 键，再 <kbd>右击</kbd> 鼠标，打开 <span class="label label-default">命令行</span> ，输入：<code>mongod.exe</code>，<kbd>回车</kbd></p>
                </blockquote>
                <blockquote>
                    <p>二、查看 <code>bin</code> 目录下的各程序：</p>
                    <table class="table table-hover table-responsive">
                        <caption>表1 - bin目录下的一些程序的含义</caption>
                        <thead>
                            <tr>
                                <th>程序名</th>
                                <th>含义</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>mongo.exe</td>
                                <td>数据库Shell</td>
                            </tr>
                            <tr>
                                <td>mongod.exe</td>
                                <td>核心数据库服务器</td>
                            </tr>
                            <tr>
                                <td>mongos.exe</td>
                                <td>自动分片程序</td>
                            </tr>
                            <tr>
                                <td>mongoimport.exe</td>
                                <td>数据导入程序</td>
                            </tr>
                            <tr>
                                <td>mongoexport.exe</td>
                                <td>数据导出程序</td>
                            </tr>
                        </tbody>
                    </table>
                </blockquote>
            </div>
        </div>
    </div>

</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#shenfen" data-toggle="collapse">身份验证和授权</a>
            </div>
            <div class="panel-body collapse" id="shenfen">
                <table class="table table-hover table-responsive">
                    <caption>表2 - Mongo角色</caption>
                    <thead>
                        <tr>
                            <th>角色名</th>
                            <th>权限简介</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>read</td>
                            <td>指定数据库，只读</td>
                        </tr>
                        <tr>
                            <td>readWrite</td>
                            <td>指定数据库，读写</td>
                        </tr>
                        <tr>
                            <td>dbAdmin</td>
                            <td>指定数据库，数据管理</td>
                        </tr>
                        <tr>
                            <td>userAdmin</td>
                            <td>指定数据库，超级管理员</td>
                        </tr>
                        <tr class="active">
                            <td>clusterAdmin</td>
                            <td>系统管理员</td>
                        </tr>
                        <tr>
                            <td>readAnydatabase</td>
                            <td>所有数据库，只读</td>
                        </tr>
                        <tr>
                            <td>readWriteAnydatabase</td>
                            <td>所有数据库，读写</td>
                        </tr>
                        <tr>
                            <td>dbAdminAnydatabase</td>
                            <td>所有数据库，数据管理</td>
                        </tr>
                        <tr>
                            <td>userAdminAnydatabase</td>
                            <td>所有数据库，超级管理员</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#shuyu" data-toggle="collapse">mongoDB和SQL中一些术语的区别</a>
            </div>
            <div class="panel-body collapse" id="shuyu">
                <table class="table table-hover table-responsive">
                    <caption>表3 - mongoDB和SQL中一些术语的区别</caption>
                    <thead>
                        <tr>
                            <th>SQL</th>
                            <th>mongo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>数据库database</td>
                            <td>数据库db</td>
                        </tr>
                        <tr>
                            <td>表table</td>
                            <td>集合collection</td>
                        </tr>
                        <tr>
                            <td>行</td>
                            <td>文档</td>
                        </tr>
                        <tr>
                            <td>列</td>
                            <td>字段</td>
                        </tr>
                        <tr>
                            <td>索引</td>
                            <td>索引</td>
                        </tr>
                        <tr>
                            <td>表内联结（and,or那些）</td>
                            <td>嵌入和引用</td>
                        </tr>
                        <tr>
                            <td>主键（由一列或多列组成）</td>
                            <td>标识符 <kbd>_id</kbd></td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>



<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#mongo_shell" data-toggle="collapse">使用 mongo Shell</a>
            </div>
            <div class="panel-body collapse" id="mongo_shell">
                <blockquote>
                    <p>1. 创建<code>C:\student.cvs</code>，记得设置编码为<code>utf-8</code>，内容如下：</p>
                    <pre>
no,name,class,gender,score
"01","张三","1","男",72
"02","李四","2","女",47
"03","王五","1","男",84
"04","陆六","2","男",69
"05","郑七","3","男",83
"06","许八","2","女",57
"07","郭九","1","女",32</pre>
                </blockquote>

                <blockquote>
                    <p>2. 打开mongod.exe</p>
                    <p><code>mongod.exe</code></p>
                </blockquote>

                <blockquote>
                    <p>3. 导入数据</p>
                    <p class="list-group-item"><code>mongoimport.exe --host localhost --db mydb --collection student --type csv --file c:\student.csv --headerline</code></p>
                    <p class="list-group-item">表示将包含<code>首行</code>的CSV文件<code>C:\student.csv</code>导入<code>localhost</code>（主机，mongoDB的默认端口是27017）的名为<code>mydb</code>的数据库中（不存在则自动创建）的<code>student</code>集合里，输出结果：</p>

                    <pre>
2018-10-19T20:38:43.823+0800    connected to: localhost
2018-10-19T20:38:44.043+0800    imported 7 documents</pre>
                </blockquote>

                <blockquote>
                    <p>4. 显示数据</p>

                    <p class="list-group-item">进入mongo shell</p>
                    <p class="list-group-item"><code>mongo.exe</code></p>
                    <pre>

MongoDB shell version v4.0.3
connecting to: mongodb://127.0.0.1:27017
Implicit session: session { "id" : UUID("5ef895cd-0728-4e83-801e-f51a94b31f19")
}
MongoDB server version: 4.0.3
Server has startup warnings:
2018-10-19T19:44:54.337+0800 I CONTROL  [initandlisten]
2018-10-19T19:44:54.337+0800 I CONTROL  [initandlisten] ** WARNING: Access contr
ol is not enabled for the database.
2018-10-19T19:44:54.337+0800 I CONTROL  [initandlisten] **          Read and wri
te access to data and configuration is unrestricted.
2018-10-19T19:44:54.337+0800 I CONTROL  [initandlisten]
---
Enable MongoDB's free cloud-based monitoring service, which will then receive an
d display
metrics about your deployment (disk utilization, CPU, operation statistics, etc)
.

The monitoring data will be available on a MongoDB website with a unique URL acc
essible to you
and anyone you share the URL with. MongoDB may use this information to make prod
uct
improvements and to suggest MongoDB products and deployment options to you.

To enable free monitoring, run the following command: db.enableFreeMonitoring()
To permanently disable this reminder, run the following command: db.disableFreeM
onitoring()
---

></pre>

                    <p class="list-group-item">打开/切换数据库userdb</p>
                    <p class="list-group-item"><code>use mydb</code></p>
                    <pre>
> use mydb
switched to db mydb
>
                    </pre>

                    <p class="list-group-item">列出当前数据库的所有集合的名称</p>
                    <p class="list-group-item"><code>show collections</code></p>
                    <pre>
> show collections
student
>
                    </pre>

                    <p class="list-group-item">显示student集合的内容</p>
                    <p class="list-group-item"><code>db.student.find()</code> </p>
                    <pre>
> db.student.find()
{ "_id" : ObjectId("5bc9d0535905bcfca4029c8a"), "no" : 1, "name" : "张三", "class" : 1, "gender" : "男", "score" : 72 }
{ "_id" : ObjectId("5bc9d0535905bcfca4029c8b"), "no" : 2, "name" : "李四", "class" : 2, "gender" : "女", "score" : 47 }
{ "_id" : ObjectId("5bc9d0535905bcfca4029c8c"), "no" : 3, "name" : "王五", "class" : 1, "gender" : "男", "score" : 84 }
{ "_id" : ObjectId("5bc9d0535905bcfca4029c8d"), "no" : 4, "name" : "陆六", "class" : 2, "gender" : "男", "score" : 69 }
{ "_id" : ObjectId("5bc9d0535905bcfca4029c8e"), "no" : 5, "name" : "郑七", "class" : 3, "gender" : "男", "score" : 83 }
{ "_id" : ObjectId("5bc9d0535905bcfca4029c8f"), "no" : 6, "name" : "许八", "class" : 2, "gender" : "女", "score" : 57 }
{ "_id" : ObjectId("5bc9d0535905bcfca4029c90"), "no" : 7, "name" : "郭九", "class" : 1, "gender" : "女", "score" : 32 }
> 
                    </pre>


                </blockquote>
            </div>
        </div>
    </div>
</section>

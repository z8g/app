<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>


<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#exp01" data-toggle="collapse">实习一 古典加密算法</a>
            </div>
            <div class="panel-body collapse" id="exp01">
                             
                <p><span class="label label-default">1. 问题描述</span></p>
                <pre>1. 理解常见古典加密算法：凯撒密码、多字母替代密码、多表替代密码；
2. 理解古典加密技术中的替换技术、置换技术。

凯撒密码是把字母表中的每个字母用该字母后的某个字母进行代替。
凯撒密码的通用加密算法是：C=E(P)=(P+k) mod 26   0&lt;k&lt;26
凯撒密码的通用解密算法是：P=D(C)=(P-k) mod 26   0&lt;k&lt;26</pre>
                <p><span class="label label-default">2． 阶梯任务</span> （实习任务按照难度渐次给出，阶梯(1)为<code>必须完成</code>的基础任务，请结合自己的实际选择后续阶梯任务）</p>
                <pre>(1)利用课本表2-1实现凯撒密码的加密、解密算法，能够根据用户选择秘钥(移位数)和明文进行加解密，用户密钥在整数范围内；
(2)对于恺撒加密实现图形界面，用户可以输入明文和密钥，在文本框中显示对应密文；
(3)实现用户对文件的操作，用户可以通过指定路径文件和密钥，加密结果存储在指定文件中；
(4)将恺撒密码扩展到任意起始位置、字符集、密钥长度，加、解密算法；
(5)学习一种中文编码方式，实现字符集加密和解密。(本项选做)。</pre>
                                 
                
                <p><span class="label label-default">3. 实现提示</span></p>
                <pre>(1)用户可以通过命令实现密钥和明文的选择；
(2)由于字母表中共有26个字符，因此，移位前应先将移动的位数(key)和26取模。Java平台中可以实现字符和整数的自动转换，因此将字符加上一个正整数代表在字母表中右移位数。如果移动的位数为负值，则代表在字母中左移位数。
(3)尽管移位前已经将移动的位数和26取模，但是通过这种方式实现的右移和左移仍可能发生超界。因此，移位后仍要判断是否超界。</pre>

                <p><span class="label label-success">4. 阶梯任务1</span></p>
                <div id="CaesarEncryption01" class="collapse"><jsp:include page="txt/CaesarEncryption01.jsp"></jsp:include></div>
                <p><span class="label label-primary" href="#CaesarEncryption01" data-toggle="collapse">展开/关闭代码</span></p>
                
            </div>
        </div>
    </div>
</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#exp02" data-toggle="collapse">实习二 分组密码加密</a>
            </div>
            <div class="panel-body collapse" id="exp02">
                
                <p><span class="label label-default">1. 问题描述</span></p>
                <pre>1. 理解对称加密算法的原理，熟悉常用的对称加密算法：DES、TripleDES、Blowfish；
2. 以DES加密算法为例，掌握分组加密算法加、解密过程的实现</pre>

                <p><span class="label label-default">2． 阶梯任务</span> （实习任务按照难度渐次给出，阶梯(1)为<code>必须完成</code>的基础任务，请结合自己的实际选择后续阶梯任务）</p>
                <pre>(1)以本地两个目录模拟两个用户，实现基本DES加密通讯，引入的包具体到类；
(2)对于DES加密实现图形界面，用户可以输入明文和密钥，在文本框中显示对应密文；
(3)实现用户对文件的操作，用户可以通过指定路径文件和密钥，加密结果存储在指定文件中；
(4)采用Socket，建立安全通信过程；
(5)将方案移植到某个web应用中。</pre>
                                 
                
                <p><span class="label label-default">3. 实现提示</span></p>
                <pre>(1)可以利用java中的KeyGenerator类创建对称秘钥，利用工厂类KeyGenerator的静态方法getInstance()获得KeyGenerator()类对象；
(2)方法getInstance()的参数为字符串类型，指定加密算法的名称如：Blowfish、DES、DESede、HmacMD5或HmacSHA1等；
(3)利用工厂类Cipher的对象可以创建密码器。同样的，getInstance()的参数为字符串类型，指定加密算法的名称。</pre>
                
            </div>
        </div>
    </div>
</section>

<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#exp03" data-toggle="collapse">实习三 基于RSA的公钥加密</a>
            </div>
            <div class="panel-body collapse" id="exp03">
                
                <p><span class="label label-default">1. 问题描述</span></p>
                <pre>1. 理解公钥密码算法，熟悉常用密码算法：RSA、椭圆曲线密码体制；
2．以RSA加密算法为例，掌握公钥密码算法加解密过程的实现</pre>

                <p><span class="label label-default">2． 阶梯任务</span> （实习任务按照难度渐次给出，阶梯(1)为<code>必须完成</code>的基础任务，请结合自己的实际选择后续阶梯任务）</p>
                <pre>(1)以本地两个目录模拟两个用户，实现基本RSA加密通讯，引入的包具体到类；
(2)对于RSA加密实现图形界面，用户可以输入明文和密钥，在文本框中显示对应密文；
(3)实现用户对文件的操作，用户可以通过指定路径文件和密钥，加密结果存储在指定文件中；
(4)采用SOCKET，建立安全通信过程；
(5)将方案移植到某个web应用中。</pre>
                                 
                
                <p><span class="label label-default">3. 实现提示</span></p>
                <pre>(1)可以利用java中的KeypairGenerator类创建公钥密钥对，工厂类KeypairGenerator的静态方法getInstance()可以获得KeypairGenerator类型对象。
(2)方法getInstance()的参数为字符串类型，指定加密算法的名称如：RSA。
(3)利用工厂类Cipher的对象创建密码器。同样的，getInstance()的参数为字符串类型，指定加密算法的名称。
(4)JSDK1.2中只是实现了RSA密钥创建，没有实现RSA算法，因此需要安装其他加密软件提供者的软件包，才能直接使用Cipher类执行加解密。
(5)RSA算法是使用整数进行加密运算的，RSA的公钥中包含两个信息：公钥对应的整数e和用于取模的整数n。对于明文m计算密文的公式是me mod n。java中的BigInteger类中定义的modPow()方法可以计算me mod n。
(6)RSA的私钥中包含两个信息：私钥对应的整数d和用于取模的整数n。计算明文的公式是：Ce mod n 。</pre>
                
            </div>
        </div>
    </div>
</section>
                
                
<section class="row">
    <div class="col-md-12">	
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="#exp04" data-toggle="collapse">实习四 基于Diffle-Human的密钥交换</a>
            </div>
            <div class="panel-body collapse" id="exp04">
                
                <p><span class="label label-default">1. 问题描述</span></p>
                <pre>1．理解密钥管理相关内容，熟悉Diffle-Human的密钥交换协议；
2．在java平台上实现基于Diffle-Human的密钥交换。

Diffle-Human算法是建立在DH公钥和私钥基础上的秘钥分配算法，如A需要和B共享密钥时，A和B各自生成DH的公钥和私钥，公钥对外公布而私钥各自秘密保存。</pre>

                <p><span class="label label-default">2． 阶梯任务</span> （实习任务按照难度渐次给出，阶梯(1)为<code>必须完成</code>的基础任务，请结合自己的实际选择后续阶梯任务）</p>
                <pre>(1)以本地两个目录模拟两个用户，实现基本DH加密通讯，引入的包具体到类；
(2)对于DH加密实现图形界面，用户可以输入明文和密钥，在文本框中显示对应密文；
(3)实现用户对文件的操作，用户可以通过指定路径文件和密钥，加密结果存储在指定文件中；
(4)采用SOCKET，建立安全通信过程；
(5)将方案移植到某个web应用中。</pre>
                                 
                
                <p><span class="label label-default">3. 实现提示</span></p>
                <pre>(1)利用公钥密码中的KeyGenerator类创建公钥密钥对，其参数指定为”DH”。另外在初始化时需要为DH指定的参数DHParameterSpec DHP=new DHParameterSpec(skip1024Modulus,skip1024Base);
(2)skip1024Modulus指定模，skip1024Base指定基数。模和基数取值在Internet协议简单密钥管理标准中已经指定，在安装JSDK后，计算机的C盘中存在C:\jsdk-1_4_0-doc\docs\guide\security\jce\JCERefGuide.html文件，其中包含密钥长度为1024的DH密钥中的模和基数的定义，可以直接复制下来，在JCERefGuide.html中查找1024 bit Diffle-Hellman modulus注释语句，将其下的skip1024ModulusBytes[]数组及BigInteger类型的skip1024Modulus和skip1024Base复制下来即可。
(3)建立两个目录A和B，模拟需要秘密通信的A、B双方，DH算法需要A和B各自生成DH公钥和私钥。
(4)java中KeyAgreement类实现了密钥协定，它使得init()方法传入自己的私钥，使用doPhase()方法传入对方的公钥，进而可以使用generateSecret()方法生成共享的信息。</pre>       
            </div>
        </div>
    </div>
</section>
           
<jsp:include page="bottom.jsp"></jsp:include>

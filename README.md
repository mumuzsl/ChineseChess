# 简述
Java GUI，中国象棋
# 思路
- 一个二维数组作为棋盘，数组中存放ChessPieces引用，ChessPieces是所有棋子的父类。
- 接口Run中的isRule方法获得两个坐标，用于判断用户点击是否符合该棋子的移动规则。
- 为每种棋子构建类，并实现Run接口。
- 每个棋子有自己阵营标识Camp，Camp是枚举：RED（红方）、BLACK（黑方）
- 每次落子后判断“将”，“帅”的存在来判断输赢。
# 预览
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806191337729.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyMjEzMDE0,size_16,color_FFFFFF,t_70)

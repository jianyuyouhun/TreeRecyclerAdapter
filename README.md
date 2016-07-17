# README #

　　开发过程中总是会遇到实现树形结构的需求，使用ExpandableListView当然是可以实现的，而最近在想使用recyclerview会不会很麻烦呢，首先上github上搜了一下，很多采用了设定不同的viewholder和onBind来解决，同时还能实现很多级的展开。可能一开始很不容易理解，而且有些时候我们只需要两层结构。所以就花了点时间写了一个demo，以一个简单的方式来实现两层结构的TreeRecyclerView，不需要去实现一个自定义的recyclerview，也不需要对Adapter的结构有太大的改动。各位将就着看吧，很多地方是可以改进的。
# 先看下效果 #

<iframe height=800f width=500 src="GIF.gif">

# 实现思路 #

　　有时候我们遇到的json数据结构就是两层的，比如一个A中含有一些属性以及一个B的数组，这样我们在构建实体类的时候也会按照同样的结构来进行，所以我们先创建一个实体类ParentEntity代码如下

<code>
	public class ParentEntity {
	    private int id;
	    private String name;
	    private List<ChildEntity> children;

	    public static class ChildEntity{
	       private int id;
	       private String name;
	    }
	}

</code>
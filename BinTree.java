public class BinTree{
    
    private Object zObject;
    private BinTree zLeftTree;
    private BinTree zRightTree;
    
    public BinTree(){
        zLeftTree = null;
        zRightTree = null;
        zObject = null;
    }
    
    public BinTree(Object pObject){
        zLeftTree = new BinTree();
        zRightTree = new BinTree();
        zObject = pObject;
    }
    
    public BinTree(Object pObject, BinTree pLeftTree, BinTree pRightTree){
        zLeftTree = pLeftTree;
        zRightTree = pRightTree;
        zObject = pObject;    
    }
    
    public boolean isEmpty(){
        return (zObject == null && zLeftTree == null && zRightTree == null);
    }
    
    public void clear(){
        zLeftTree = null;
        zRightTree = null;
        zObject = null;
    }
    
    public void setRootItem(Object pObject){
        zObject = pObject;
    }
    
    public Object getRootItem(){
        return zObject;
    }
    
    public void setLeftTree(BinTree pTree){
        if(zObject != null || zLeftTree != null || zRightTree != null){
            zLeftTree = pTree;
        }
    }
    
    public void setRightTree(BinTree pTree){
        if(zObject != null || zLeftTree != null || zRightTree != null){
            zRightTree = pTree;
        }
    }
    
    public BinTree getLeftTree(){
        if(zObject != null || zLeftTree != null || zRightTree != null){
            return zLeftTree;
        }
        return null;
    }
    
    public BinTree getRightTree(){
        if(zObject != null || zLeftTree != null || zRightTree != null){
            return zRightTree;
        }
        return null;
    }
    
}
package core;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class LoadStringsImplTest
{
    LoadStringsImpl loadStrings = Mockito.spy(LoadStringsImpl.class);
    List<LoadUpdate> updateList = loadStrings.loadUpdateList;

    @Test
    public void testString(){
        String str1 = "qwer";
        String str2 = new String("qwer");
        String str3 = "qwer";
        String str4 = new String("qwer");
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str3);
        System.out.println(str2 == str4);
    }

    @Test
    public void testUpdateData() {
        //GIVEN
        LoadUpdate loadUpdate = new LoadUpdate("u",9,1,"bid");
        String[] array = {"u","9","1","bid"};
        //WHEN
        loadStrings.updateData(array);
        //THEN
        Assert.assertNotNull(updateList);
        Assert.assertEquals(updateList.get(0).getPriceUpdate(), loadUpdate.getPriceUpdate());
        Assert.assertEquals(updateList.get(0).getTypeUpdate(), loadUpdate.getTypeUpdate());
        Assert.assertEquals(updateList.get(0).getSizeUpdate(), loadUpdate.getSizeUpdate());
        Assert.assertEquals(updateList.get(0).getLetterUpdate(), loadUpdate.getLetterUpdate());
    }

    @Test
    public void testDeleteData(){
        //GIVEN
        LoadUpdate loadUpdate = new LoadUpdate("u",9,0,"bid");
        String[] array = {"o","sell","1"};
        String[] arrayMore = {"u","9","1","bid"};
        //WHEN
        loadStrings.updateData(arrayMore);
        loadStrings.deleteData(array);
        //THEN
        Assert.assertNotNull(updateList);
        Assert.assertEquals(updateList.get(0).getSizeUpdate(), loadUpdate.getSizeUpdate());
        Mockito.verify(loadStrings, Mockito.times(1)).deleteData(array);
    }

    @Test
    public void testQueryData(){
        //GIVEN
        LoadUpdate loadUpdate = new LoadUpdate("u",10,2,"bid");
        String[] array = {"q","best_bid"};
        String[] arrayLess = {"u","9","1","bid"};
        String[] arrayMore = {"u","10","2","bid"};
        //WHEN
        loadStrings.updateData(arrayLess);
        loadStrings.updateData(arrayMore);
        loadStrings.queryData(array);
        //THEN
        Assert.assertNotNull(updateList);
        Assert.assertEquals(updateList.get(1).getSizeUpdate(), loadUpdate.getSizeUpdate());
        Assert.assertEquals(updateList.get(1).getPriceUpdate(), loadUpdate.getPriceUpdate());
        Mockito.verify(loadStrings, Mockito.times(1)).queryData(array);
    }


}

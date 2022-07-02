/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Post;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Linhnvhdev
 */
public class PostDBContext extends DBContext {

    public int insertPost(String postTitle,int parentPostID, int userID, String postDetail, boolean edited, int postLike, Date createdDate, String postCategory,int courseID) {
        try {
            String sql="INSERT INTO [dbo].[Post]\n" +
                    "           ([Post_Title]\n" +
                    "           ,[Post_Parent_ID]\n" +
                    "           ,[Creator_ID]\n" +
                    "           ,[Post_Detail]\n" +
                    "           ,[IsEdited]\n" +
                    "           ,[Post_Like]\n" +
                    "           ,[Created_Date]\n" +
                    "           ,[Category]\n" +
                    "           ,[Related_Course_ID])\n" +
                    "	OUTPUT INSERTED.Post_ID\n"+
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, postTitle);
            stm.setInt(2,parentPostID);
            stm.setInt(3,userID);
            stm.setString(4,postDetail);
            stm.setBoolean(5,edited);
            stm.setInt(6,postLike);
            stm.setDate(7,createdDate);
            stm.setString(8,postCategory);
            stm.setInt(9,courseID);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public Post getPost(int postID){
        try {
            String sql="SELECT [Post_ID]\n" +
            "      ,[Post_Title]\n" +
            "      ,[Post_Parent_ID]\n" +
            "      ,[Creator_ID]\n" +
            "      ,[Post_Detail]\n" +
            "      ,[IsEdited]\n" +
            "      ,[Post_Like]\n" +
            "      ,[Created_Date]\n" +
            "      ,[Category]\n" +
            "      ,[Related_Course_ID]\n" +
            "  FROM [dbo].[Post]\n" +
            "WHERE Post_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,postID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Post post = new Post();
                post.setID(rs.getInt("Post_ID"));
                post.setTitle(rs.getString("Post_Title"));
                Post parentPost = getPost(rs.getInt("Post_Parent_ID"));
                post.setParentPost(parentPost);
                post.setCreatorID(rs.getInt("Creator_ID"));
                post.setPostDetail(rs.getString("Post_Detail"));
                post.setIsEdited(rs.getBoolean("IsEdited"));
                int like = rs.getInt("Post_Like");
                post.setLikes(getLikes(postID));
                post.setCreatedDate(rs.getDate("Created_Date"));
                post.setCategory(rs.getString("Category"));
                post.setRelatedCourseID(rs.getInt("Related_Course_ID"));
                return post;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Post> getChildPost(int postID){
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String sql="SELECT [Post_ID]\n" +
            "      ,[Post_Title]\n" +
            "      ,[Post_Parent_ID]\n" +
            "      ,[Creator_ID]\n" +
            "      ,[Post_Detail]\n" +
            "      ,[IsEdited]\n" +
            "      ,[Post_Like]\n" +
            "      ,[Created_Date]\n" +
            "      ,[Category]\n" +
            "      ,[Related_Course_ID]\n" +
            "  FROM [dbo].[Post]\n" +
            "WHERE [Post_Parent_ID] = ?" ;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,postID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Post post = new Post();
                int curPostID = rs.getInt("Post_ID");
                post.setID(curPostID);
                post.setTitle(rs.getString("Post_Title"));
                Post parentPost = getPost(rs.getInt("Post_Parent_ID"));
                post.setParentPost(parentPost);
                post.setCreatorID(rs.getInt("Creator_ID"));
                post.setPostDetail(rs.getString("Post_Detail"));
                post.setIsEdited(rs.getBoolean("IsEdited"));
                int postLike = rs.getInt("Post_Like");
                post.setLikes(getLikes(curPostID));
                post.setCreatedDate(rs.getDate("Created_Date"));
                post.setCategory(rs.getString("Category"));
                post.setRelatedCourseID(rs.getInt("Related_Course_ID"));
                posts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }
    
    public ArrayList<Post> getChildPost(int postID,String category){
        ArrayList<Post> posts = new ArrayList<>();
        try {
            String sql="SELECT [Post_ID]\n" +
            "      ,[Post_Title]\n" +
            "      ,[Post_Parent_ID]\n" +
            "      ,[Creator_ID]\n" +
            "      ,[Post_Detail]\n" +
            "      ,[IsEdited]\n" +
            "      ,[Post_Like]\n" +
            "      ,[Created_Date]\n" +
            "      ,[Category]\n" +
            "      ,[Related_Course_ID]\n" +
            "  FROM [dbo].[Post]\n" +
            "WHERE [Post_Parent_ID] = ? AND category = '"+category+"'" ;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,postID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Post post = new Post();
                int curPostID = rs.getInt("Post_ID");
                post.setID(curPostID);
                post.setTitle(rs.getString("Post_Title"));
                Post parentPost = getPost(rs.getInt("Post_Parent_ID"));
                post.setParentPost(parentPost);
                post.setCreatorID(rs.getInt("Creator_ID"));
                post.setPostDetail(rs.getString("Post_Detail"));
                post.setIsEdited(rs.getBoolean("IsEdited"));
                int postLike = rs.getInt("Post_Like");
                post.setLikes(getLikes(curPostID));
                post.setCreatedDate(rs.getDate("Created_Date"));
                post.setCategory(rs.getString("Category"));
                post.setRelatedCourseID(rs.getInt("Related_Course_ID"));
                posts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }
    
    public int getNumChildPost(int postID){
        try {
            String sql="SELECT COUNT(*)\n" +
            "  FROM [dbo].[Post]\n" +
            "   WHERE [Post_Parent_ID] = ?" ;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,postID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public ArrayList<Pair<Post,Integer>> getAllPost(){
        ArrayList<Pair<Post,Integer>> posts = new ArrayList<>();
        try {
            String sql="SELECT main.[Post_ID]\n" +
            "	  ,main.[Post_Title]\n" +
            "      ,main.[Post_Parent_ID]\n" +
            "      ,main.[Creator_ID]\n" +
            "      ,main.[Post_Detail]\n" +
            "      ,main.[IsEdited]\n" +
            "      ,main.[Post_Like]\n" +
            "      ,main.[Created_Date]\n" +
            "      ,main.[Category]\n" +
            "      ,main.[Related_Course_ID]\n" +
            "	  ,COUNT(sub.Post_ID) AS \"Replies\"	\n" +
            "FROM Post main LEFT JOIN Post sub ON main.Post_ID = sub.Post_Parent_ID\n" +
            "WHERE main.[Post_Parent_ID] = 0\n" +
            "GROUP BY main.Post_ID,main.Post_Title,main.Post_Parent_ID,main.Creator_ID,main.Post_Detail,\n" +
            "	main.IsEdited,main.Post_Like,main.Created_Date,main.Category,main.Related_Course_ID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Post post = new Post();
                int curPostID = rs.getInt("Post_ID");
                post.setID(curPostID);
                post.setTitle(rs.getString("Post_Title"));
                Post parentPost = getPost(rs.getInt("Post_Parent_ID"));
                post.setParentPost(parentPost);
                post.setCreatorID(rs.getInt("Creator_ID"));
                post.setPostDetail(rs.getString("Post_Detail"));
                post.setIsEdited(rs.getBoolean("IsEdited"));
                int postLike = rs.getInt("Post_Like");
                post.setLikes(getLikes(curPostID));
                post.setCreatedDate(rs.getDate("Created_Date"));
                post.setCategory(rs.getString("Category"));
                post.setRelatedCourseID(rs.getInt("Related_Course_ID"));
                int replies = rs.getInt("Replies");
                posts.add(new Pair<Post,Integer>(post,replies));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public boolean likeUnlikePost(int postID, int userID) {
        try {
            String sql="IF (NOT EXISTS(SELECT * FROM User_Post WHERE [User_ID] = ? AND Post_ID = ?))\n" +
            "BEGIN \n" +
            "    INSERT INTO [dbo].[User_Post]\n" +
            "           ([User_ID]\n" +
            "           ,[Post_ID]\n" +
            "           ,[IsLike])\n" +
            "	OUTPUT inserted.IsLike\n" +
            "     VALUES\n" +
            "           (?\n" +
            "           ,?\n" +
            "           ,1)\n" +
            "END \n" +
            "ELSE \n" +
            "BEGIN \n" +
            "    UPDATE User_Post \n" +
            "    SET IsLike = ~ IsLike\n" +
            "	OUTPUT inserted.IsLike\n" +
            "	WHERE [User_ID] = ? AND Post_ID = ?\n" +
            "END ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, postID);
            stm.setInt(3, userID);
            stm.setInt(4, postID);
            stm.setInt(5, userID);
            stm.setInt(6, postID);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            if(rs.next()){
                return rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getLikes(int postID){
        try {
            String sql="SELECT COUNT(*) \"Like\"\n" +
                    "FROM User_Post\n" +
                    "WHERE Post_ID = ? AND IsLike = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean getLikeStatus(int postID,int userID){
        try {
            String sql="SELECT IsLike\n" +
            "FROM User_Post\n" +
            "WHERE [User_ID] = ? AND Post_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, postID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getBoolean(1);
            }
            else return false;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int insertComment(int postID, int userID, String commentContent, Date createdDate) {
        String title = "Comment of user " + userID + " for post " + postID; 
        return insertPost(title,postID,userID,commentContent,false,0,createdDate,"Comment",0);
    }
    
    public boolean getLikesPosts(HashMap<Integer,Boolean> postLikes, int postID,int userID){
        try {
            String sql="  SELECT u.[User_ID],p.Post_ID,\n" +
            "	ISNULL((SELECT(SELECT IsLike FROM User_Post WHERE [User_ID] = u.[User_ID] AND Post_ID = p.Post_ID)),0) \"IsLike\"\n" +
            "  FROM [User] u,Post p\n" +
            "  WHERE u.[User_ID] = ? AND (p.Post_ID = ? OR p.Post_Parent_ID = ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, postID);
            stm.setInt(3, postID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int uID = rs.getInt("User_ID");
                int pID = rs.getInt("Post_ID");
                boolean isLike = rs.getBoolean("IsLike");
                postLikes.put(pID, isLike);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean getCommentPosts(HashMap<Integer, Integer> postComments, int postID) {
        try {
            String sql="SELECT Post_ID,\n" +
            "		(SELECT COUNT(*) \n" +
            "	  FROM Post WHERE \n" +
            "	  Post_Parent_ID = p.Post_ID AND Category = 'Comment') \"Comments\"\n" +
            "  FROM Post p\n" +
            "  WHERE Post_ID = ? OR Post_Parent_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postID);
            stm.setInt(2, postID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int pID = rs.getInt("Post_ID");
                int comments = rs.getInt("Comments");
                postComments.put(pID, comments);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getNumComment(int postID) {
        try {
            String sql="SELECT COUNT(*) \"Like\"\n" +
"                FROM Post\n" +
"                WHERE Post_Parent_ID = ? AND Category = 'Comment'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean updatePost(String postTitle,int parentPostID, int userID, String postDetail, boolean edited, int postLike, Date createdDate, String postCategory,int courseID){
        try {
            String sql="UPDATE [dbo].[Post]\n" +
                    "   SET [Post_Parent_ID] = ?\n" +
                    "      ,[Creator_ID] = ?\n" +
                    "      ,[Post_Detail] = ?\n" +
                    "      ,[IsEdited] = ?\n" +
                    "      ,[Post_Like] = ?\n" +
                    "      ,[Created_Date] = ?\n" +
                    "      ,[Category] = ?\n" +
                    "      ,[Post_Title] = ?\n" +
                    "      ,[Related_Course_ID] = ?\n" +
                    " WHERE Post_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, parentPostID);
            stm.setInt(2, userID);
            stm.setString(3, postDetail);
            stm.setBoolean(4, edited);
            stm.setInt(5, postLike);
            stm.setDate(6, createdDate);
            stm.setString(7, postCategory);
            stm.setString(8, postTitle);
            stm.setInt(9, courseID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updatePostContent(int postID, String content){
        try {
            String sql="UPDATE [dbo].[Post]\n" +
                    "   SET [Post_Detail] = ?\n" +
                    " WHERE Post_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, content);
            stm.setInt(2, postID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletePost(int postID){
        try {
            ArrayList<Post> childPosts = getChildPost(postID);
            for(Post post : childPosts){
                deletePost(post.getID());
            }
            String sql="DELETE FROM [dbo].[Post]\n" +
                        "      WHERE Post_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

package com.aojing.redstore.goods.ctrl;

import com.aojing.redstore.goods.common.Result;
import com.aojing.redstore.goods.dto.CommentDto;
import com.aojing.redstore.goods.pojo.GoodsComentInfo;
import com.aojing.redstore.goods.service.GoodsComentInfoService;
import com.aojing.redstore.goods.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gexiao
 * @date 2018/12/10 10:10
 */
@RestController
@RequestMapping("/api/comment")
public class GoodsCommentCtrl {

    @Autowired
    private GoodsComentInfoService comentInfoService;


    @PostMapping("/add")
    public Result comment(@RequestBody CommentDto commentDto) {
        GoodsComentInfo comentInfo = new GoodsComentInfo();
        BeanUtils.copyProperties(commentDto, comentInfo);
        return comentInfoService.comment(comentInfo);
    }
    @PostMapping("/discomment")
    public Result discomment(String  id,String commenterId) {
        return comentInfoService.discomment(id,commenterId);
    }

    @GetMapping("getAllComment")
    public Result<List<CommentVo>> getAllComment(String goodsId){
        Result<List<CommentVo>> CommentVoList = comentInfoService.getAllCommentByGoodsId(goodsId);
        return CommentVoList;

    }
}

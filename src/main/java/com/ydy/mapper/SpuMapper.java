package com.ydy.mapper;

import org.apache.ibatis.annotations.Param;

import com.ydy.mapper.base.BaseMapper;
import com.ydy.model.Spu;
import com.ydy.vo.SpuVo;

public interface SpuMapper extends BaseMapper<Spu> {

	SpuVo selectSpuById(@Param("spuId") Long spuId, @Param("spuStatus") Integer spuStatus);
}
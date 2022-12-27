package kr.worthseeing.status.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStatus is a Querydsl query type for Status
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStatus extends EntityPathBase<Status> {

    private static final long serialVersionUID = 257571769L;

    public static final QStatus status = new QStatus("status");

    public final ListPath<kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting, kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting> blockGroupWaitingList = this.<kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting, kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting>createList("blockGroupWaitingList", kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting.class, kr.worthseeing.blockGroupWaiting.entity.QBlockGroupWaiting.class, PathInits.DIRECT2);

    public final ListPath<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock> blockList = this.<kr.worthseeing.block.entity.Block, kr.worthseeing.block.entity.QBlock>createList("blockList", kr.worthseeing.block.entity.Block.class, kr.worthseeing.block.entity.QBlock.class, PathInits.DIRECT2);

    public final ListPath<kr.worthseeing.event.coupon.entity.Coupon, kr.worthseeing.event.coupon.entity.QCoupon> couponList = this.<kr.worthseeing.event.coupon.entity.Coupon, kr.worthseeing.event.coupon.entity.QCoupon>createList("couponList", kr.worthseeing.event.coupon.entity.Coupon.class, kr.worthseeing.event.coupon.entity.QCoupon.class, PathInits.DIRECT2);

    public final StringPath firstCode = createString("firstCode");

    public final ListPath<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify> notifyList = this.<kr.worthseeing.notify.entity.Notify, kr.worthseeing.notify.entity.QNotify>createList("notifyList", kr.worthseeing.notify.entity.Notify.class, kr.worthseeing.notify.entity.QNotify.class, PathInits.DIRECT2);

    public final StringPath secondCode = createString("secondCode");

    public final NumberPath<Integer> status_seq = createNumber("status_seq", Integer.class);

    public QStatus(String variable) {
        super(Status.class, forVariable(variable));
    }

    public QStatus(Path<? extends Status> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatus(PathMetadata metadata) {
        super(Status.class, metadata);
    }

}


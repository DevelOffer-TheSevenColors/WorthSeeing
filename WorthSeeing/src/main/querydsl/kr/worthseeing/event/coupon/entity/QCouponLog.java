package kr.worthseeing.event.coupon.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponLog is a Querydsl query type for CouponLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponLog extends EntityPathBase<CouponLog> {

    private static final long serialVersionUID = -1402968817L;

    public static final QCouponLog couponLog = new QCouponLog("couponLog");

    public final NumberPath<Integer> coupon_seq = createNumber("coupon_seq", Integer.class);

    public final NumberPath<Integer> couponLog_seq = createNumber("couponLog_seq", Integer.class);

    public final NumberPath<Integer> couponPrice = createNumber("couponPrice", Integer.class);

    public final StringPath couponSerialNum = createString("couponSerialNum");

    public final DateTimePath<java.util.Date> couponUsedDate = createDateTime("couponUsedDate", java.util.Date.class);

    public final StringPath userid = createString("userid");

    public QCouponLog(String variable) {
        super(CouponLog.class, forVariable(variable));
    }

    public QCouponLog(Path<? extends CouponLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponLog(PathMetadata metadata) {
        super(CouponLog.class, metadata);
    }

}


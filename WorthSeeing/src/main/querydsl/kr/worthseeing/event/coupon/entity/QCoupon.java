package kr.worthseeing.event.coupon.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = -963246603L;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final NumberPath<Integer> coupon_seq = createNumber("coupon_seq", Integer.class);

    public final NumberPath<Integer> couponPrice = createNumber("couponPrice", Integer.class);

    public final StringPath couponSerialNum = createString("couponSerialNum");

    public final DateTimePath<java.util.Date> couponUsedDate = createDateTime("couponUsedDate", java.util.Date.class);

    public final StringPath userid = createString("userid");

    public QCoupon(String variable) {
        super(Coupon.class, forVariable(variable));
    }

    public QCoupon(Path<? extends Coupon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoupon(PathMetadata metadata) {
        super(Coupon.class, metadata);
    }

}


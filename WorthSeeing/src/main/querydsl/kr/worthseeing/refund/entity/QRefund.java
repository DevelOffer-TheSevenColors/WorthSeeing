package kr.worthseeing.refund.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRefund is a Querydsl query type for Refund
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefund extends EntityPathBase<Refund> {

    private static final long serialVersionUID = -790513595L;

    public static final QRefund refund = new QRefund("refund");

    public final NumberPath<Integer> blockGroup_seq = createNumber("blockGroup_seq", Integer.class);

    public final NumberPath<Integer> blockGroupWaiting_seq = createNumber("blockGroupWaiting_seq", Integer.class);

    public final NumberPath<Integer> refund_seq = createNumber("refund_seq", Integer.class);

    public final DateTimePath<java.util.Date> refundDate = createDateTime("refundDate", java.util.Date.class);

    public final NumberPath<Integer> refundPrice = createNumber("refundPrice", Integer.class);

    public QRefund(String variable) {
        super(Refund.class, forVariable(variable));
    }

    public QRefund(Path<? extends Refund> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRefund(PathMetadata metadata) {
        super(Refund.class, metadata);
    }

}


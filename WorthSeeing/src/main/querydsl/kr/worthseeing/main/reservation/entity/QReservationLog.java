package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationLog is a Querydsl query type for ReservationLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationLog extends EntityPathBase<ReservationLog> {

    private static final long serialVersionUID = -1758077948L;

    public static final QReservationLog reservationLog = new QReservationLog("reservationLog");

    public final NumberPath<Integer> groupblock_seq = createNumber("groupblock_seq", Integer.class);

    public final NumberPath<Integer> groupblockLog_seq = createNumber("groupblockLog_seq", Integer.class);

    public final NumberPath<Integer> reservationLog_seq = createNumber("reservationLog_seq", Integer.class);

    public final DateTimePath<java.util.Date> reservationTime = createDateTime("reservationTime", java.util.Date.class);

    public final NumberPath<Integer> startPrice = createNumber("startPrice", Integer.class);

    public final NumberPath<Integer> userCnt = createNumber("userCnt", Integer.class);

    public QReservationLog(String variable) {
        super(ReservationLog.class, forVariable(variable));
    }

    public QReservationLog(Path<? extends ReservationLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationLog(PathMetadata metadata) {
        super(ReservationLog.class, metadata);
    }

}


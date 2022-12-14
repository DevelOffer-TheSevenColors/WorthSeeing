package kr.worthseeing.main.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationUserId is a Querydsl query type for ReservationUserId
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationUserId extends EntityPathBase<ReservationUserId> {

    private static final long serialVersionUID = -1807532666L;

    public static final QReservationUserId reservationUserId = new QReservationUserId("reservationUserId");

    public final NumberPath<Integer> reservation_seq = createNumber("reservation_seq", Integer.class);

    public final NumberPath<Integer> ReservationUserId_seq = createNumber("ReservationUserId_seq", Integer.class);

    public final DateTimePath<java.util.Date> reservationUserIdDate = createDateTime("reservationUserIdDate", java.util.Date.class);

    public final NumberPath<Integer> userid = createNumber("userid", Integer.class);

    public QReservationUserId(String variable) {
        super(ReservationUserId.class, forVariable(variable));
    }

    public QReservationUserId(Path<? extends ReservationUserId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationUserId(PathMetadata metadata) {
        super(ReservationUserId.class, metadata);
    }

}


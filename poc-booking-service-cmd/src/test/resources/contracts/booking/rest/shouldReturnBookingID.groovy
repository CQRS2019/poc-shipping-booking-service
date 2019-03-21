package contracts.booking.rest

import org.springframework.cloud.contract.spec.Contract

def contractDsl = Contract.make {

    description("""
return a bookingID

given:
	booking/cargo information
when:
	post
then:
	you'll get a bookingID
""")
    request {
        method 'POST'
        url '/command/bookings/'

        body(file('bookingCreation.json'))

        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
                "69855ab4-4370-4c44-848e-5b13fb1dfbcb"
        )
        headers {
            contentType(applicationJson())
        }
    }
}
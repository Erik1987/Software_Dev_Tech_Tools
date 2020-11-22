// Example modified from https://github.com/mqttjs/MQTT.js#example
const mqtt = require('mqtt');
// normally const port = Number(process.env.PORT) || 3000;
// const port = process.env.PORT || 3000;
// Vehicle positioning for ongoing buses at Lauttasaari bridge
// I've used coordinates 60.176363, 24.950165 for test purposes due to slow traffic
const myTopic = '/hfp/v2/journey/ongoing/vp/bus/+/+/+/+/+/+/+/+/60;24/18/69/27/#';
const hslClient = mqtt.connect('mqtts://mqtt.hsl.fi:8883');

hslClient.on('connect', function () {
    hslClient.subscribe(myTopic, function (err) {
        if (!err) {
            console.log('Connected!');
        } else {
            console.log(err);
        }
    })
});

hslClient.on('message', function (topic, message) {
    // todo: handle the traffic jam and speeding logic here
    // author erik
    let json = JSON.parse(message.toString());
    let speed = json.VP.spd;
    const mosquittoClient = mqtt.connect('mqtt://test.mosquitto.org:1883');
    const testTopic = '/swd4tn023/ERIK_TIE_TESTI_POLIISINA/traffic/jam';
    const testTopic2 = '/swd4tn023/ERIK_TIE_TESTI_POLIISINA/traffic/speeding';
    var type;

    console.log('Speed (km/h): ' + speed * 3.6) + '\n';
    if(speed > 8.33){
        console.log('Speeding violation! \nOver the limit (km/h) is: ' + (speed - 8.33) * 3.6) + '\n';

        var msg2 = {VP:{
            "oper": `${json.VP.oper}`,
            "veh": `${json.VP.veh}`,
            "lat": `${json.VP.lat}`,
            "long": `${json.VP.long}`,
            "spd": `${json.VP.spd}`,
            "cause": "Potential speeding"
        }};
        type = 'speeding';

    }else if(speed <= 8.33) {
        // Traffic jam part of the code
       
        var msg = {VP:{
            "oper": `${json.VP.oper}`,
            "veh": `${json.VP.veh}`,
            "lat": `${json.VP.lat}`,
            "long": `${json.VP.long}`,
            "spd": `${json.VP.spd}`,
            "cause": "Potential traffic jam"
        }};
        type = 'jam';
 
    }

    mosquittoClient.on('connect', function () {

        if (mosquittoClient.connected==true) {

            switch(type) {
                case 'jam':
                mosquittoClient.publish(testTopic, JSON.stringify(msg.VP) ,function (err) {
                    if (!err) {
                        console.log('Published! \n');
                        // tulostus ei vaikuta lähetettävään dataan
                        console.log(msg.VP);
                        console.log('\n');
                        // use this if needed
                        //console.log(JSON.stringify(msg.VP));
                        
                    } else {
                        console.log(err);
                    }
                });
                break;

                case 'speeding':
                mosquittoClient.publish(testTopic2, JSON.stringify(msg2.VP) ,function (err) {
                    if (!err) {
                        console.log('Published! \n');
                            // tulostus ei vaikuta lähetettävään dataan
                            console.log(msg2.VP);
                            console.log('\n');
                            // use this if needed
                            //console.log(JSON.stringify(msg.VP));
                       
                    } else {
                        console.log(err);
                    }
                });
                break;
            } 
                
            }
    });

    console.log('\nAlkuperäinen data: \n');
    console.log(message.toString() + '\n');

});
// test data for test.mosquitto.org
// mqtt subscribe -h test.mosquitto.org -p 1883 -l mqtt -v -t "/swd4tn023/ERIK_TIE_TESTI_POLIISINA/traffic/#"
// satuin huomaamaan bugin tai rajan mosquittossa 10 testauksen jälkeen. Eli ei anna yli 10 publishaa jst syystä.
// nyt se ei anna mun ollenkaan publishaa wtf, aikaisemmin toimi kaikki mut nyt mä oon estetty.

/* 
{ Error: connect ECONNREFUSED 5.196.95.208:1883
    at TCPConnectWrap.afterConnect [as oncomplete] (net.js:1107:14)
  errno: 'ECONNREFUSED',
  code: 'ECONNREFUSED',
  syscall: 'connect',
  address: '5.196.95.208',
  port: 1883 }

*/
module.exports = {hslClient};
//app.listen(port, () => console.log(`running on port ${port}`));

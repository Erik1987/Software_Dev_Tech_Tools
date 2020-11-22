function createEventDateFilter(min = '0000-00-00', max = '9999-12-31') {
    return function (event) {
        let { starting_day } = event.event_dates;
        return min <= starting_day && starting_day <= max;
    }
}

function eventDateComparator(event1, event2) {
    let event1date = event1.event_dates.starting_day || '';
    let event2date = event2.event_dates.starting_day || '';

    // see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort and
    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/localeCompare
    return event1date.localeCompare(event2date);
}

module.exports = {
    createEventDateFilter,
    eventDateComparator
};

